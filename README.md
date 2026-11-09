# Monster Fighter — Demo MVVM + Navegación (T07)

Proyecto de clase para 2º DAM (Programación Multimedia y Dispositivos Móviles).

La app es deliberadamente **muy sencilla**: aparece un monstruo, lo tocas, le quitas entre 1 y 3 puntos de vida y, cuando cae, sale otro y sube el contador de bajas. Ya está. No hay más juego que eso.

Y es a propósito: el monstruo es solo una **excusa**. Lo que interesa es tener otra implementación, pequeña y fácil de seguir, de dos cosas que se repiten en todo el tema:

1. **Arquitectura MVVM** con `ViewModel` + `StateFlow` + un `UiState` inmutable.
2. **Navegación en Compose** con `NavHost`, rutas sin *magic strings* y un `ViewModel` compartido entre pantallas.

Como el dominio no tiene ninguna complejidad, toda la atención queda en la arquitectura.

---

## Cómo está organizado: una rama por paso

> ⚠️ **TODO:** la estructura de ramas está pendiente de actualizar. Puede que lo que pone esta sección y lo que hay de verdad en cada rama no coincida del todo.

El proyecto se recorre por ramas. Cada una parte de la anterior y arregla un problema de la versión previa.

| Rama | Qué contiene | Qué se aprende |
|---|---|---|
| `starter` | Esqueleto: modelo, repositorio *fake* y recursos (sprites). | Punto de partida para el alumnado. |
| `main` | La versión "fullera": funciona, pero el estado y la lógica de negocio están **dentro del Composable**. La UI ya está troceada en componentes (`MonsterCard`, `HpBar`, `StatsPanel`). | Ver por qué esto **no** escala. |
| `develop-mvvm` | Refactor a MVVM: aparecen `MonsterUiState` y `MonsterViewModel`. | Separar estado, lógica y vista. |
| `develop-nav2` | Se añade una segunda pantalla (inventario) y la navegación entre ambas. | `NavHost`, destinos y compartir `ViewModel`. |

> `main-original` guarda la primera versión de la vista, antes de reorganizar paquetes. Las ramas locales `develop` y `develop2` equivalen a `develop-mvvm` y `develop-nav2`.

```bash
git switch develop-mvvm   # por ejemplo
```

---

## Paso 1 — La versión "fullera" (`main`)

En `ui/screens/MainScreen.kt` la pantalla lo hace **todo**:

- Guarda el estado con `remember { mutableStateOf(...) }`.
- Define `onAttack()` (daño, muerte, siguiente monstruo) **dentro** del Composable.
- Pinta la UI pasando datos a componentes "tontos".

Funciona, y precisamente por eso sirve para hablar de sus problemas:

- **Al rotar la pantalla el monstruo se resetea.** `remember` no sobrevive a la recreación de la Activity. Si se cambia por `rememberSaveable`, peta, porque el estado contiene una clase que no se puede guardar en un `Bundle`.
- **Testeabilidad nula.** La lógica de combate no se puede probar sin levantar la UI.
- **Responsabilidades mezcladas.** La vista sabe de repositorios, de reglas del juego y de cómo pintarse.

Lo que sí está bien desde el principio son los **componentes "tontos"** de `ui/components/`. Solo reciben datos y una lambda, y cada uno tiene su `@Preview`:

```
MonsterCard(name, currentHp, maxHp, spriteId, onAttack)
 ├─ HpBar(currentHp, maxHp)
StatsPanel(bodyCount)
```

## Paso 2 — MVVM (`develop-mvvm`)

```
        eventos (onAttack)
  View ───────────────────────▶ ViewModel ──▶ Repository
 (Compose) ◀─────────────────── (StateFlow)    (datos fake)
        estado (MonsterUiState)
```

- **`MonsterUiState`**: `data class` inmutable con todo lo que la pantalla necesita para pintarse (`name`, `currentHp`, `maxHp`, `spriteId`, `bodyCount`).
- **`MonsterViewModel`**: expone `val uiState: StateFlow<MonsterUiState>` (solo lectura). Por dentro usa un `MutableStateFlow` privado y modifica el estado con `_uiState.update { it.copy(...) }`. Toda la lógica de combate vive aquí.
- **La vista** se limita a hacer `collectAsState()` y a llamar a `viewModel.onAttack()`.

Resultado: el estado **sobrevive a la rotación** (el `ViewModel` no se destruye con la Activity), la lógica se puede testear sin UI y cada capa tiene una sola responsabilidad.

## Paso 3 — Navegación (`develop-nav2`)

Se añade una `InventoryScreen` y un botón para ir a ella desde la pantalla principal.

- **`NavigationDestination`**: interfaz con `route` y `titleRes`. Cada pantalla declara su destino como un `object`, así no hay *magic strings* sueltos por el código.
- **`MonsterClickerNavHost`**: define el grafo con `NavHost` y `composable(route = ...)`.
- **Un `ViewModel` compartido**: se crea una sola vez en el NavHost y se pasa a las dos pantallas, así que ambas ven el mismo estado.
- **Las pantallas no conocen el `NavController`**: reciben una lambda (`onNavigateToInventory`) y es el NavHost quien decide adónde ir.
- **`Scaffold` y `TopAppBar`** se suben al Composable raíz de la app, que adapta el título y la flecha atrás según la pantalla.

---

## Estructura de paquetes

```
net.iessochoa.sergiocontreras.demomvvmt07_1
├── MainActivity.kt          # Solo hace setContent { MonsterFighterApp() }
├── MonsterFighterApp.kt     # Composable raíz: Scaffold (+ NavHost en nav2)
├── model/Monster.kt         # Modelo de dominio
├── data/MonsterRepository.kt# "BBDD" falsa: un object con una lista de monstruos
└── ui/
    ├── components/          # Componentes "tontos" reutilizables
    ├── screens/             # Pantallas
    └── theme/               # Tema Material 3
```

## Requisitos

- Android Studio reciente (AGP 9.x, Kotlin 2.x).
- `minSdk 26`, `targetSdk 37`.
- Jetpack Compose + Material 3.

Abre el proyecto, elige la rama y ejecuta. Si solo quieres ver los componentes, también puedes usar los `@Preview`.

## Ideas para practicar

- Añadir un tercer monstruo al repositorio (y su sprite en `res/drawable-nodpi`).
- Mover el rango de daño (`1..3`) al `UiState` y mostrarlo en `StatsPanel` en lugar del texto fijo.
- Escribir un test unitario de `MonsterViewModel.onAttack()`.
- Añadir una tercera pantalla (por ejemplo, un bestiario) al grafo de navegación.