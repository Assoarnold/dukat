package org.jetbrains.dukat.tsmodel

import org.jetbrains.dukat.astCommon.Declaration
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration

data class ParameterDeclaration(
        val name: String,
        val type: ParameterValueDeclaration,
        val initializer: ExpressionDeclaration?,

        val vararg: Boolean,
        val optional: Boolean
) : Declaration