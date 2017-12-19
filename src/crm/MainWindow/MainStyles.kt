package crm.MainWindow

import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class MainStyles : Stylesheet() {
    companion object {
        val zip by cssclass()
    }

    init {
        s(form) {
            padding = box(0.px)
            prefWidth = 1200.px


            s(zip) {
                maxWidth = 700.px
                minWidth = maxWidth
            }
        }
    }
}