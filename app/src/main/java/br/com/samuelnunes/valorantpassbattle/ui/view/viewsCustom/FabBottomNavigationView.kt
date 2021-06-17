package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.animation.doOnEnd
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.extensions.getColorFromAttr
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class FabBottomNavigationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : HideBottomNavigation(context, attrs, defStyleAttr) {

    //    https://medium.com/@vadim.zhukov/easy-way-to-make-curved-bottomnavigationview-with-floatingactionbutton-12b979009e64
    private var topCurvedEdgeTreatment: BottomAppBarTopEdgeTreatment
    private var materialShapeDrawable: MaterialShapeDrawable
    private var fabSize = 0F
    private var fabCradleMargin = 0F
    private var fabCradleRoundedCornerRadius = 0F
    private var cradleVerticalOffset = 0F

    init {
        val ta =
            context.theme.obtainStyledAttributes(attrs, R.styleable.FabBottomNavigationView, 0, 0)
        fabSize = ta.getDimension(R.styleable.FabBottomNavigationView_fab_size, 0F)
        fabCradleMargin = ta.getDimension(R.styleable.FabBottomNavigationView_fab_cradle_margin, 0F)
        fabCradleRoundedCornerRadius =
            ta.getDimension(
                R.styleable.FabBottomNavigationView_fab_cradle_rounded_corner_radius,
                0F
            )
        cradleVerticalOffset =
            ta.getDimension(R.styleable.FabBottomNavigationView_cradle_vertical_offset, 0F)

        topCurvedEdgeTreatment = BottomAppBarTopEdgeTreatment(fabCradleMargin, fabCradleRoundedCornerRadius, cradleVerticalOffset).apply {
            fabDiameter = fabSize
        }

        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setTopEdge(topCurvedEdgeTreatment)
            .build()

        materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(context.getColorFromAttr(R.attr.colorPrimaryVariant))
            paintStyle = Paint.Style.FILL_AND_STROKE
        }
        background = materialShapeDrawable
    }

    fun transform(fab: FloatingActionButton, status: Boolean) {
        if (status) {
            ValueAnimator.ofFloat(materialShapeDrawable.interpolation, 1F).apply {
                doOnEnd {
                    fab.show()
                    slideUp()
                }
                start()
            }
        } else {
            ValueAnimator.ofFloat(materialShapeDrawable.interpolation, 0F).apply {
                doOnEnd {
                    fab.hide()
                    slideDown()
                }
                start()
            }
        }
    }
}
