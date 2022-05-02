package com.stochanskyi.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Issue.Companion.create
import com.intellij.psi.PsiElement
import org.jetbrains.uast.*
import java.util.logging.Level
import kotlin.math.log

class LintIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(TestIssue)
}

val TestIssue = create(
    "Test Issue",
    "Test Issue",
    "Test Issue",
    Implementation(
        DisposableDetector::class.java,
        Scope.JAVA_FILE_SCOPE
    )
)

class DisposableHandler(
    private val context: JavaContext
) : UElementHandler() {

    override fun visitExpression(node: UExpression) {
        super.visitExpression(node)
    }

    override fun visitExpressionList(node: UExpressionList) {
        java.util.logging.Logger.getLogger("SOME_TAG").log(Level.INFO, "log message")
        context.report(TestIssue,
            Location.create(context.file),
            "Test descr")
    }

    override fun visitCallExpression(node: UCallExpression) {
        context.report(TestIssue,
            context.getCallLocation(node, true, true),
            "Test descr")
        super.visitCallExpression(node)
    }

    override fun visitBlockExpression(node: UBlockExpression) {
        super.visitBlockExpression(node)
    }

    override fun visitDeclarationsExpression(node: UDeclarationsExpression) {
        super.visitDeclarationsExpression(node)
    }

    override fun visitDeclaration(node: UDeclaration) {
        super.visitDeclaration(node)
    }


}

class DisposableDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }

    override fun getApplicableMethodNames(): List<String> {
        return listOf("startUpdate")
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return DisposableHandler(context)
    }

    override fun getApplicableCallOwners(): List<String>? {
        return super.getApplicableCallOwners()
    }

}

//class TestDetector : Detector(), XmlScanner {
//    override fun getApplicableCallNames(): List<String>? {
//        return super.getApplicableCallNames()
//    }
//
//    override fun getApplicableElements(): Collection<String>? {
//        return listOf("manifest")
//    }
//
//
//    override fun afterCheckFile(context: Context) {
//        context.report(
//            TestIssue,
//            Location.create(context.file),
//            "Test descr"
//        )
//    }
//
//    override fun visitReference(
//        context: JavaContext,
//        reference: UReferenceExpression,
//        referenced: PsiElement
//    ) {
//        super.visitReference(context, reference, referenced)
//    }
//}