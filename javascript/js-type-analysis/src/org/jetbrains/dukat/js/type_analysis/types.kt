package org.jetbrains.dukat.js.type_analysis

import org.jetbrains.dukat.astCommon.MemberEntity
import org.jetbrains.dukat.astCommon.TopLevelEntity
import org.jetbrains.dukat.js.type_analysis.constraint.container.ReturnConstraintContainer
import org.jetbrains.dukat.panic.raiseConcern
import org.jetbrains.dukat.tsmodel.BlockDeclaration
import org.jetbrains.dukat.tsmodel.ClassDeclaration
import org.jetbrains.dukat.tsmodel.ConstructorDeclaration
import org.jetbrains.dukat.tsmodel.EnumDeclaration
import org.jetbrains.dukat.tsmodel.ExportAssignmentDeclaration
import org.jetbrains.dukat.tsmodel.ModuleDeclaration
import org.jetbrains.dukat.tsmodel.SourceFileDeclaration
import org.jetbrains.dukat.tsmodel.SourceSetDeclaration
import org.jetbrains.dukat.tsmodel.ExpressionStatementDeclaration
import org.jetbrains.dukat.tsmodel.FunctionDeclaration
import org.jetbrains.dukat.tsmodel.ImportEqualsDeclaration
import org.jetbrains.dukat.tsmodel.InterfaceDeclaration
import org.jetbrains.dukat.tsmodel.PropertyDeclaration
import org.jetbrains.dukat.tsmodel.ReturnStatementDeclaration
import org.jetbrains.dukat.tsmodel.TypeAliasDeclaration
import org.jetbrains.dukat.tsmodel.VariableDeclaration
import org.jetbrains.dukat.tsmodel.types.IndexSignatureDeclaration

fun FunctionDeclaration.introduceTypes() : FunctionDeclaration {
    if (this.body != null) {
        val returnTypeConstraints = ReturnConstraintContainer()

        for(statement in this.body!!.statements) {
            when(statement) {
                is ReturnStatementDeclaration -> returnTypeConstraints += statement.expression.calculateConstraints()
            }
        }

        return copy(
                type = returnTypeConstraints.resolveToType()
        )
    } else {
        return this;
    }
}

fun ConstructorDeclaration.introduceTypes() : ConstructorDeclaration {
    return this
}

fun MemberEntity.introduceTypes(): MemberEntity {
    return when (this) {
        is FunctionDeclaration -> this.introduceTypes()
        is ConstructorDeclaration -> this.introduceTypes()
        is PropertyDeclaration -> this
        is IndexSignatureDeclaration -> this
        else -> raiseConcern("Unexpected member entity type <${this.javaClass}>") { this }
    }
}

fun ClassDeclaration.introduceTypes() = copy(members = members.map { it.introduceTypes() })

fun BlockDeclaration.introduceTypes() = copy(statements = statements.map { it.introduceTypes() })

fun TopLevelEntity.introduceTypes(): TopLevelEntity {
    return when (this) {
        is FunctionDeclaration -> this.introduceTypes()
        is ClassDeclaration -> this.introduceTypes()
        is BlockDeclaration -> this.introduceTypes()
        is ModuleDeclaration -> this.introduceTypes()
        is InterfaceDeclaration -> this //TODO check if this needs modification
        is VariableDeclaration,
        is EnumDeclaration,
        is ExportAssignmentDeclaration,
        is ImportEqualsDeclaration,
        is TypeAliasDeclaration,
        is ReturnStatementDeclaration,
        is ExpressionStatementDeclaration -> this
        else -> raiseConcern("Unexpected top level entity type <${this.javaClass}>") { this }
    }
}

fun ModuleDeclaration.introduceTypes(): ModuleDeclaration = copy(declarations = declarations.map { it.introduceTypes() })

fun SourceFileDeclaration.introduceTypes(): SourceFileDeclaration {
    return if (fileName.endsWith(".d.ts")) { //TODO replace with ".js"
        copy(root = root.introduceTypes())
    } else this
}

fun SourceSetDeclaration.introduceTypes() = copy(sources = sources.map{ it.introduceTypes() })
