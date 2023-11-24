package ru.trailblazers.musicapp.ui.theme

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Groups: ImageVector
    get() {
        if (_groups != null) return _groups!!
        _groups = materialIcon(name = "Outlined.Groups") {
            materialPath {
                moveTo(12.0F, 12.75F)
                curveToRelative(1.63F, 0.0F, 3.07F, 0.39F, 4.24F, 0.9F)
                curveToRelative(1.08F, 0.48F, 1.76F, 1.56F, 1.76F, 2.73F)
                lineTo(18.0F, 18.0F)
                horizontalLineTo(6.0F)
                lineToRelative(0.0F, -1.61F)
                curveToRelative(0.0F, -1.18F, 0.68F, -2.26F, 1.76F, -2.73F)
                curveTo(8.93F, 13.14F, 10.37F, 12.75F, 12.0F, 12.75F)
                close()
                moveTo(4.0F, 13.0F)
                curveToRelative(1.1F, 0.0F, 2.0F, -0.9F, 2.0F, -2.0F)
                curveToRelative(0.0F, -1.1F, -0.9F, -2.0F, -2.0F, -2.0F)
                reflectiveCurveToRelative(-2.0F, 0.9F, -2.0F, 2.0F)
                curveTo(2.0F, 12.1F, 2.9F, 13.0F, 4.0F, 13.0F)
                close()
                moveTo(5.13F, 14.1F)
                curveTo(4.76F, 14.04F, 4.39F, 14.0F, 4.0F, 14.0F)
                curveToRelative(-0.99F, 0.0F, -1.93F, 0.21F, -2.78F, 0.58F)
                curveTo(0.48F, 14.9F, 0.0F, 15.62F, 0.0F, 16.43F)
                verticalLineTo(18.0F)
                lineToRelative(4.5F, 0.0F)
                verticalLineToRelative(-1.61F)
                curveTo(4.5F, 15.56F, 4.73F, 14.78F, 5.13F, 14.1F)
                close()
                moveTo(20.0F, 13.0F)
                curveToRelative(1.1F, 0.0F, 2.0F, -0.9F, 2.0F, -2.0F)
                curveToRelative(0.0F, -1.1F, -0.9F, -2.0F, -2.0F, -2.0F)
                reflectiveCurveToRelative(-2.0F, 0.9F, -2.0F, 2.0F)
                curveTo(18.0F, 12.1F, 18.9F, 13.0F, 20.0F, 13.0F)
                close()
                moveTo(24.0F, 16.43F)
                curveToRelative(0.0F, -0.81F, -0.48F, -1.53F, -1.22F, -1.85F)
                curveTo(21.93F, 14.21F, 20.99F, 14.0F, 20.0F, 14.0F)
                curveToRelative(-0.39F, 0.0F, -0.76F, 0.04F, -1.13F, 0.1F)
                curveToRelative(0.4F, 0.68F, 0.63F, 1.46F, 0.63F, 2.29F)
                verticalLineTo(18.0F)
                lineToRelative(4.5F, 0.0F)
                verticalLineTo(16.43F)
                close()
                moveTo(12.0F, 6.0F)
                curveToRelative(1.66F, 0.0F, 3.0F, 1.34F, 3.0F, 3.0F)
                curveToRelative(0.0F, 1.66F, -1.34F, 3.0F, -3.0F, 3.0F)
                reflectiveCurveToRelative(-3.0F, -1.34F, -3.0F, -3.0F)
                curveTo(9.0F, 7.34F, 10.34F, 6.0F, 12.0F, 6.0F)
                close()
            }
        }
        return _groups!!
    }

private var _groups: ImageVector? = null
