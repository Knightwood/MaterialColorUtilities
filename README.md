# MaterialColorUtilities

谷歌 material color 工具类

来源：  https://github.com/material-foundation/material-color-utilities

## 依赖

版本[![](https://jitpack.io/v/Knightwood/MaterialColorUtilities.svg)](https://jitpack.io/#Knightwood/MaterialColorUtilities)

```kotlin
implementation("com.github.knightwood:MaterialColorUtilities:1.0")
```

## 使用

在主题中使用colorScheme

```kotlin
MaterialTheme(
    colorScheme = mDynamicColorScheme(
                        themeColorSeed.toColor,
                        isDark,
                        PaletteStyle.values()[paletteStyleIndex],
                        contrastValue
     ),
    content = content
)
```

自己写一个DynamicColorScheme.kt 文件
```kotlin
/**
 * 支持的主题色调色板
 * 1. TONAL_SPOT 是一种明艳度中等的调色板，使用与 system_accent1_0 颜色类似的 system_accent3_0 颜色（从 Android 12 版本继承而来，并进行了略微调整）。
 * 2. VIBRANT 是一种明艳度偏高的调色板，和谐地融合了颜色之间的细微变化。
 * 3. EXPRESSIVE 是一种明艳度偏高的调色板，用于将各种意想不到的独特强调色搭配在一起。
 * 4. SPRITZ 是一种明艳度偏低的调色板，可在不同颜色之间产生一种柔和的水洗效果。
 * 5. RAINBOW 同时使用了彩色色调和中性表面，可为用户打造更微妙的色彩体验。
 * 不建议与基于壁纸的颜色提取过程一起使用，而应与静态的颜色主题搭配使用。
 * 6. FRUIT_SALAD 提供了两种色调，让用户更具表现力。
 * 不建议与基于壁纸的颜色提取过程一起使用，而应与静态的颜色主题搭配使用。
 */
enum class PaletteStyle {
    TonalSpot,
    Neutral,
    Vibrant,
    Expressive,
    Rainbow,
    FruitSalad,
    Monochrome,
    Fidelity,
    Content
}

/**
 * 使用此方法生成colorScheme
 */
fun mDynamicColorScheme(
    keyColor: Color,
    isDark: Boolean,
    style: PaletteStyle = PaletteStyle.TonalSpot,
    contrastLevel: Double = ThemeHelper.DEFAULT_HIGH_CONTRAST_VALUE_0
): ColorScheme {
    val hct = Hct.fromInt(keyColor.toArgb())
    val colors = MaterialDynamicColors()
    val scheme = when (style) {
        PaletteStyle.TonalSpot -> SchemeTonalSpot(hct, isDark, contrastLevel)
        PaletteStyle.Neutral -> SchemeNeutral(hct, isDark, contrastLevel)
        PaletteStyle.Vibrant -> SchemeVibrant(hct, isDark, contrastLevel)
        PaletteStyle.Expressive -> SchemeExpressive(hct, isDark, contrastLevel)
        PaletteStyle.Rainbow -> SchemeRainbow(hct, isDark, contrastLevel)
        PaletteStyle.FruitSalad -> SchemeFruitSalad(hct, isDark, contrastLevel)
        PaletteStyle.Monochrome -> SchemeMonochrome(hct, isDark, contrastLevel)
        PaletteStyle.Fidelity -> SchemeFidelity(hct, isDark, contrastLevel)
        PaletteStyle.Content -> SchemeContent(hct, isDark, contrastLevel)
    }

    return ColorScheme(
        background = Color(colors.background().getArgb(scheme)),
        error = Color(colors.error().getArgb(scheme)),
        errorContainer = Color(colors.errorContainer().getArgb(scheme)),
        inverseOnSurface = Color(colors.inverseOnSurface().getArgb(scheme)),
        inversePrimary = Color(colors.inversePrimary().getArgb(scheme)),
        inverseSurface = Color(colors.inverseSurface().getArgb(scheme)),
        onBackground = Color(colors.onBackground().getArgb(scheme)),
        onError = Color(colors.onError().getArgb(scheme)),
        onErrorContainer = Color(colors.onErrorContainer().getArgb(scheme)),
        onPrimary = Color(colors.onPrimary().getArgb(scheme)),
        onPrimaryContainer = Color(colors.onPrimaryContainer().getArgb(scheme)),
        onSecondary = Color(colors.onSecondary().getArgb(scheme)),
        onSecondaryContainer = Color(colors.onSecondaryContainer().getArgb(scheme)),
        onSurface = Color(colors.onSurface().getArgb(scheme)),
        onSurfaceVariant = Color(colors.onSurfaceVariant().getArgb(scheme)),
        onTertiary = Color(colors.onTertiary().getArgb(scheme)),
        onTertiaryContainer = Color(colors.onTertiaryContainer().getArgb(scheme)),
        outline = Color(colors.outline().getArgb(scheme)),
        outlineVariant = Color(colors.outlineVariant().getArgb(scheme)),
        primary = Color(colors.primary().getArgb(scheme)),
        primaryContainer = Color(colors.primaryContainer().getArgb(scheme)),
        scrim = Color(colors.scrim().getArgb(scheme)),
        secondary = Color(colors.secondary().getArgb(scheme)),
        secondaryContainer = Color(colors.secondaryContainer().getArgb(scheme)),
        surface = Color(colors.surface().getArgb(scheme)),
        surfaceTint = Color(colors.surfaceTint().getArgb(scheme)),
        surfaceVariant = Color(colors.surfaceVariant().getArgb(scheme)),
        tertiary = Color(colors.tertiary().getArgb(scheme)),
        tertiaryContainer = Color(colors.tertiaryContainer().getArgb(scheme))
    )
}
```
