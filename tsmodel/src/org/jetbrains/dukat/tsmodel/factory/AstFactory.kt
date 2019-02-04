package org.jetbrains.dukat.tsmodel.factory

import org.jetbrains.dukat.astCommon.AstNode
import org.jetbrains.dukat.astCommon.MemberDeclaration
import org.jetbrains.dukat.astCommon.TopLevelDeclaration
import org.jetbrains.dukat.tsmodel.CallSignatureDeclaration
import org.jetbrains.dukat.tsmodel.ClassDeclaration
import org.jetbrains.dukat.tsmodel.ConstructorDeclaration
import org.jetbrains.dukat.tsmodel.DocumentRootDeclaration
import org.jetbrains.dukat.tsmodel.ExportAssignmentDeclaration
import org.jetbrains.dukat.tsmodel.ExpressionDeclaration
import org.jetbrains.dukat.tsmodel.FunctionDeclaration
import org.jetbrains.dukat.tsmodel.HeritageClauseDeclaration
import org.jetbrains.dukat.tsmodel.InterfaceDeclaration
import org.jetbrains.dukat.tsmodel.MethodSignatureDeclaration
import org.jetbrains.dukat.tsmodel.ModifierDeclaration
import org.jetbrains.dukat.tsmodel.ParameterDeclaration
import org.jetbrains.dukat.tsmodel.PropertyDeclaration
import org.jetbrains.dukat.tsmodel.TokenDeclaration
import org.jetbrains.dukat.tsmodel.TypeAliasDeclaration
import org.jetbrains.dukat.tsmodel.TypeParameterDeclaration
import org.jetbrains.dukat.tsmodel.VariableDeclaration
import org.jetbrains.dukat.tsmodel.types.FunctionTypeDeclaration
import org.jetbrains.dukat.tsmodel.types.IndexSignatureDeclaration
import org.jetbrains.dukat.tsmodel.types.ObjectLiteralDeclaration
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration
import org.jetbrains.dukat.tsmodel.types.StringTypeDeclaration
import org.jetbrains.dukat.tsmodel.types.TypeDeclaration
import org.jetbrains.dukat.tsmodel.types.UnionTypeDeclaration

class AstFactory : AstNodeFactory<AstNode> {

    override fun createExportAssignmentDeclaration(name: String) = ExportAssignmentDeclaration(name)

    override fun createTokenDeclaration(value: String) = TokenDeclaration(value)

    override fun createHeritageClauseDeclaration(name: String, typeArguments: List<TokenDeclaration>, extending: Boolean) = HeritageClauseDeclaration(name, typeArguments, extending)

    override fun createTypeAliasDeclaration(aliasName: String, typeParameters: List<TokenDeclaration>, typeReference: ParameterValueDeclaration) = TypeAliasDeclaration(aliasName, typeParameters, typeReference)

    override fun createStringTypeDeclaration(tokens: List<String>) = StringTypeDeclaration(tokens)

    override fun createIndexSignatureDeclaration(indexTypes: List<ParameterDeclaration>, returnType: ParameterValueDeclaration) = IndexSignatureDeclaration(indexTypes, returnType)

    override fun createCallSignatureDeclaration(
            parameters: List<ParameterDeclaration>,
            type: ParameterValueDeclaration,
            typeParameters: List<TypeParameterDeclaration>
    ) = CallSignatureDeclaration(parameters, type, typeParameters)

    override fun createModifierDeclaration(token: String) = ModifierDeclaration(token)

    override fun createClassDeclaration(
            name: String,
            members: List<MemberDeclaration>,
            typeParameters: List<TypeParameterDeclaration>,
            parentEntities: List<HeritageClauseDeclaration>,
            modifiers: List<ModifierDeclaration>
    ): AstNode = ClassDeclaration(name, members, typeParameters, parentEntities, modifiers)

    override fun createObjectLiteral(members: List<MemberDeclaration>) = ObjectLiteralDeclaration(members)

    override fun createInterfaceDeclaration(name: String, members: List<MemberDeclaration>, typeParameters: List<TypeParameterDeclaration>, parentEntities: List<HeritageClauseDeclaration>): AstNode = InterfaceDeclaration(name, members, typeParameters, parentEntities)

    override fun declareVariable(name: String, type: ParameterValueDeclaration, modifiers: List<ModifierDeclaration>): AstNode = VariableDeclaration(name, type, modifiers)
    override fun declareProperty(
            name: String,
            type: ParameterValueDeclaration,
            parameters: List<TypeParameterDeclaration>,
            optional: Boolean,
            modifiers: List<ModifierDeclaration>
    ) = PropertyDeclaration(name, type, parameters, optional, modifiers)

    override fun createExpression(kind: TypeDeclaration, meta: String?) = ExpressionDeclaration(kind, meta)

    override fun createConstructorDeclaration(parameters: List<ParameterDeclaration>, type: ParameterValueDeclaration, typeParameters: List<TypeParameterDeclaration>, modifiers: List<ModifierDeclaration>) = ConstructorDeclaration(parameters, type, typeParameters, modifiers)

    override fun createFunctionDeclaration(
            name: String,
            parameters: Array<ParameterDeclaration>,
            type: ParameterValueDeclaration,
            typeParameters: Array<TypeParameterDeclaration>,
            modifiers: List<ModifierDeclaration>
    ): FunctionDeclaration {
        return FunctionDeclaration(name, parameters.toList(), type, typeParameters.toList(), modifiers)
    }

    override fun createMethodSignatureDeclaration(name: String, parameters: Array<ParameterDeclaration>, type: ParameterValueDeclaration, typeParameters: Array<TypeParameterDeclaration>, optional: Boolean, modifiers: List<ModifierDeclaration>) = MethodSignatureDeclaration(name, parameters.toList(), type, typeParameters.toList(), optional, modifiers)

    override fun createFunctionTypeDeclaration(parameters: Array<ParameterDeclaration>, type: ParameterValueDeclaration) = FunctionTypeDeclaration(parameters.toList(), type)

    override fun createUnionDeclaration(params: List<ParameterValueDeclaration>) = UnionTypeDeclaration(params)
    override fun createTypeDeclaration(value: String, params: Array<ParameterValueDeclaration>) = TypeDeclaration(value, params)

    override fun createParameterDeclaration(name: String, type: ParameterValueDeclaration, initializer: ExpressionDeclaration?, vararg: Boolean, optional: Boolean) = ParameterDeclaration(name, type, initializer, vararg, optional)

    override fun createDocumentRoot(packageName: String, declarations: Array<TopLevelDeclaration>, modifiers: List<ModifierDeclaration>) = DocumentRootDeclaration(packageName, declarations.toList(), modifiers)

    override fun createTypeParam(name: String, constraints: Array<ParameterValueDeclaration>) = TypeParameterDeclaration(name, constraints.toList())
}