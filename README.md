# ⚠️ Rama antigua — no la uses

Esta rama (`develop-mvvm`) es de un **curso anterior** y se conserva solo como archivo histórico.

Está hecha con versiones viejas de todo (AGP 8.13, Kotlin 2.0, Compose BOM 2024.09, `targetSdk 36`), así que con el Android Studio actual **lo normal es que no compile o dé guerra**. Además, su estructura ya no coincide con lo que se explica en clase.

## Qué tienes que usar en su lugar

| Dónde | Para qué |
|---|---|
| Rama `starter` | **Tu rama de trabajo.** Aquí intentas hacer tú el refactor a MVVM. |
| Tag `starting-point` | Punto de partida de `starter`, por si quieres volver a empezar desde cero. |
| Rama `main` | La solución propuesta, explicada **commit a commit** (`Paso 0`, `Paso 1`…). |

```bash
git switch starter
```

Toda la explicación está en el `README.md` de `main`.
