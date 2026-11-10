# Monster Fighter — Demo MVVM + Navegación (T07)

Proyecto de clase para 2º DAM (Programación Multimedia y Dispositivos Móviles).

La app es deliberadamente **muy sencilla**: aparece un monstruo, lo tocas, le quitas entre 1 y 3 puntos de vida y, cuando cae, sale otro y sube el contador de bajas. Ya está. No hay más juego que eso.

Y es a propósito: el monstruo es solo una **excusa**. Lo que interesa es tener otra implementación, pequeña y fácil de seguir, de dos cosas que se repiten en todo el tema:

1. **Arquitectura MVVM** con `ViewModel` + `StateFlow` + un `UiState` inmutable.
2. **Navegación en Compose** *(todavía no está en `main`; de momento el proyecto cubre la parte de MVVM)*.

Como el dominio no tiene ninguna complejidad, toda la atención queda en la arquitectura.

---

## Cómo está organizado

| Dónde | Para qué |
|---|---|
| Rama `starter` | **Tu rama de trabajo.** La app funciona, pero el estado y la lógica están dentro del Composable. Aquí es donde tienes que intentar hacer tú el refactor a MVVM. |
| Tag `starting-point` | Marca el punto de partida de `starter`. Si te lías y quieres volver a empezar desde cero, vuelve aquí. |
| Rama `main` | **Mi solución propuesta**, explicada **commit a commit** (`Paso 0`, `Paso 1`…). Cada commit es un paso pequeño, y el código lleva comentarios `Paso N` explicando qué se hace y por qué. |

```bash
# Empezar a trabajar
git switch starter

# Volver al punto de partida si algo se rompe (crea una rama nueva desde el tag)
git switch -c mi-intento starting-point

# Ver la solución paso a paso
git log --oneline starting-point..main
git show <hash-del-paso>          # qué cambia en ese paso
git switch --detach <hash-del-paso>  # dejar el proyecto tal y como estaba en ese paso
```

> 💡 La forma recomendada es: intenta tú cada paso en `starter` y, si te atascas, mira **solo el commit de ese paso** en `main`, no la solución entera.

### ⚠️ Ramas antiguas: no las uses

`develop-mvvm`, `develop-nav2` y `main-original` son de un curso anterior. Están hechas con versiones viejas de todo (AGP 8.13, Kotlin 2.0, Compose BOM 2024.09, `targetSdk 36`, Navigation 2), así que con el Android Studio actual **lo normal es que no compilen o den guerra**. Se quedan solo como archivo histórico. Trabaja siempre a partir de `starter` / `main`.

---

## El punto de partida (`starter`)

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

## La solución paso a paso (`main`)

```
        eventos (onAttack)
  View ───────────────────────▶ ViewModel ──▶ Repository
 (Compose) ◀─────────────────── (StateFlow)    (datos fake)
        estado (MainScreenUiState)
```

| Paso | Qué se hace |
|---|---|
| **0** | Añadir las dependencias de `lifecycle-viewmodel-compose` y `lifecycle-runtime-compose`. |
| **1** | Crear `MainScreenUiState`: una `data class` con lo que necesita la pantalla para pintarse. |
| **2** | Crear `MainScreenViewModel` con su `_uiState` privado (`MutableStateFlow`) y su `uiState` público (`StateFlow`), con el estado inicial sacado del repositorio. |
| **3** | Sacar el estado de la vista: `MainScreen` recibe el `uiState` y el `ViewModel` se instancia en `MonsterFighterApp`. (La app deja de atacar temporalmente: es normal). |
| **4** | Arreglo: al `UiState` le faltaba la vida actual del monstruo (`currentMonsterHP`). Pasa a menudo, se vuelve atrás y se corrige. |
| **5** | Mover la lógica de ataque al `ViewModel` con `_uiState.update { it.copy(...) }`. |
| **6** | `MainScreen` recibe `onAttack` como lambda y se conecta con `viewModel.onAttack()` desde `MonsterFighterApp`. |

Resultado: el estado **sobrevive a la rotación** (el `ViewModel` no se destruye con la Activity), la lógica se puede testear sin UI y cada capa tiene una sola responsabilidad.

---

## Estructura de paquetes

```
net.iessochoa.sergiocontreras.demomvvmt07_1
├── MainActivity.kt           # Solo hace setContent { MonsterFighterApp() }
├── MonsterFighterApp.kt      # Composable raíz: Scaffold + ViewModel
├── model/Monster.kt          # Modelo de dominio
├── data/MonsterRepository.kt # "BBDD" falsa: un object con una lista de monstruos
└── ui/
    ├── MainScreenUiState.kt  # (main) Estado de la pantalla
    ├── MainScreenViewModel.kt# (main) Lógica + StateFlow
    ├── components/           # Componentes "tontos" reutilizables
    ├── screens/              # Pantallas
    └── theme/                # Tema Material 3
```

## Requisitos

- Android Studio reciente (AGP 9.x, Kotlin 2.x).
- `minSdk 26`, `targetSdk 37`.
- Jetpack Compose + Material 3.

Abre el proyecto, elige la rama y ejecuta. Si solo quieres ver los componentes, también puedes usar los `@Preview`.

## Ideas para practicar

- Añadir un tercer monstruo al repositorio (y su sprite en `res/drawable-nodpi`).
- Mover el rango de daño (`1..3`) al `UiState` y mostrarlo en `StatsPanel` en lugar del texto fijo.
- Escribir un test unitario de `MainScreenViewModel.onAttack()`.
