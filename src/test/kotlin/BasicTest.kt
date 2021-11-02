import androidx.compose.material.Text
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import org.junit.Rule
import org.junit.Test

class BasicTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test() {
        composeTestRule.setContent {
            Text("Hello World")
        }

       assertText("Hello World")
    }

    private fun assertText(text: String, index: Int = 0) {
        composeTestRule.onNode(isRoot()).onChildAt(index).assertTextEquals(text)
    }
}