/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.dokka

import org.jetbrains.dokka.model.DAnnotation
import org.jetbrains.dokka.model.DClass
import org.jetbrains.dokka.model.DClasslike
import org.jetbrains.dokka.model.DEnum
import org.jetbrains.dokka.model.DFunction
import org.jetbrains.dokka.model.DInterface
import org.jetbrains.dokka.model.DModule
import org.jetbrains.dokka.model.DObject
import org.jetbrains.dokka.model.DPackage
import org.jetbrains.dokka.model.DParameter
import org.jetbrains.dokka.model.DProperty
import org.jetbrains.dokka.model.DTypeAlias
import org.jetbrains.dokka.model.Documentable
import org.jetbrains.dokka.model.SourceSetDependent
import org.jetbrains.dokka.model.WithChildren
import org.jetbrains.dokka.model.doc.A
import org.jetbrains.dokka.model.doc.B
import org.jetbrains.dokka.model.doc.Big
import org.jetbrains.dokka.model.doc.BlockQuote
import org.jetbrains.dokka.model.doc.Br
import org.jetbrains.dokka.model.doc.Caption
import org.jetbrains.dokka.model.doc.Cite
import org.jetbrains.dokka.model.doc.CodeBlock
import org.jetbrains.dokka.model.doc.CodeInline
import org.jetbrains.dokka.model.doc.CustomDocTag
import org.jetbrains.dokka.model.doc.CustomTagWrapper
import org.jetbrains.dokka.model.doc.Dd
import org.jetbrains.dokka.model.doc.Description
import org.jetbrains.dokka.model.doc.Dfn
import org.jetbrains.dokka.model.doc.Dir
import org.jetbrains.dokka.model.doc.Div
import org.jetbrains.dokka.model.doc.Dl
import org.jetbrains.dokka.model.doc.DocTag
import org.jetbrains.dokka.model.doc.DocumentationLink
import org.jetbrains.dokka.model.doc.DocumentationNode
import org.jetbrains.dokka.model.doc.Dt
import org.jetbrains.dokka.model.doc.Em
import org.jetbrains.dokka.model.doc.Font
import org.jetbrains.dokka.model.doc.Footer
import org.jetbrains.dokka.model.doc.Frame
import org.jetbrains.dokka.model.doc.FrameSet
import org.jetbrains.dokka.model.doc.H1
import org.jetbrains.dokka.model.doc.H2
import org.jetbrains.dokka.model.doc.H3
import org.jetbrains.dokka.model.doc.H4
import org.jetbrains.dokka.model.doc.H5
import org.jetbrains.dokka.model.doc.H6
import org.jetbrains.dokka.model.doc.Head
import org.jetbrains.dokka.model.doc.Header
import org.jetbrains.dokka.model.doc.HorizontalRule
import org.jetbrains.dokka.model.doc.Html
import org.jetbrains.dokka.model.doc.I
import org.jetbrains.dokka.model.doc.IFrame
import org.jetbrains.dokka.model.doc.Img
import org.jetbrains.dokka.model.doc.Index
import org.jetbrains.dokka.model.doc.Input
import org.jetbrains.dokka.model.doc.Li
import org.jetbrains.dokka.model.doc.Link
import org.jetbrains.dokka.model.doc.Listing
import org.jetbrains.dokka.model.doc.Main
import org.jetbrains.dokka.model.doc.Menu
import org.jetbrains.dokka.model.doc.Meta
import org.jetbrains.dokka.model.doc.Nav
import org.jetbrains.dokka.model.doc.NoFrames
import org.jetbrains.dokka.model.doc.NoScript
import org.jetbrains.dokka.model.doc.Ol
import org.jetbrains.dokka.model.doc.P
import org.jetbrains.dokka.model.doc.Pre
import org.jetbrains.dokka.model.doc.Script
import org.jetbrains.dokka.model.doc.Section
import org.jetbrains.dokka.model.doc.Small
import org.jetbrains.dokka.model.doc.Span
import org.jetbrains.dokka.model.doc.Strikethrough
import org.jetbrains.dokka.model.doc.Strong
import org.jetbrains.dokka.model.doc.Sub
import org.jetbrains.dokka.model.doc.Sup
import org.jetbrains.dokka.model.doc.TBody
import org.jetbrains.dokka.model.doc.TFoot
import org.jetbrains.dokka.model.doc.THead
import org.jetbrains.dokka.model.doc.Table
import org.jetbrains.dokka.model.doc.Td
import org.jetbrains.dokka.model.doc.Text
import org.jetbrains.dokka.model.doc.Th
import org.jetbrains.dokka.model.doc.Title
import org.jetbrains.dokka.model.doc.Tr
import org.jetbrains.dokka.model.doc.Tt
import org.jetbrains.dokka.model.doc.U
import org.jetbrains.dokka.model.doc.Ul
import org.jetbrains.dokka.model.doc.Var
import org.jetbrains.dokka.model.firstMemberOfTypeOrNull
import org.jetbrains.dokka.model.withDescendants
import org.jetbrains.dokka.plugability.DokkaContext
import org.jetbrains.dokka.transformers.documentation.DocumentableTransformer

class OudsComponentDocumentableTransformer(val context: DokkaContext) : DocumentableTransformer {

    override fun invoke(original: DModule, context: DokkaContext): DModule = original.transform() as DModule

    private fun <T : Documentable> T.transform(): Documentable {
        return when (this) {
            is DModule -> copy(packages = packages.map { it.transform() as DPackage })
            is DPackage -> copy(
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty },
                typealiases = typealiases.map { it.transform() as DTypeAlias }
            )
            is DClass -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DEnum -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DInterface -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DObject -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DTypeAlias -> copy(documentation = replaceDesignDocTags())
            is DAnnotation -> copy(
                documentation = replaceDesignDocTags(),
                classlikes = classlikes.map { it.transform() as DClasslike },
                functions = functions.map { it.transform() as DFunction },
                properties = properties.map { it.transform() as DProperty }
            )
            is DFunction -> copy(documentation = replaceDesignDocTags())
            is DProperty -> copy(documentation = replaceDesignDocTags())
            is DParameter -> copy(documentation = replaceDesignDocTags())
            else -> this
        }
    }

    private fun Documentable.replaceDesignDocTags(): SourceSetDependent<DocumentationNode> {
        val designVersionRegex = "Design version: (.*)".toRegex()
        val designGuidelinesRegex = "Design guidelines: (.*)".toRegex()

        return documentation.mapValues { (_, node) ->
            val nodeChildren = node.children.flatMap { tagWrapper ->
                if (tagWrapper is Description) {
                    val tagWrapperRoot = tagWrapper.root
                        .removeDescendant { designVersionRegex.find(it) != null }
                        .removeDescendant { designGuidelinesRegex.find(it) != null }

                    val version = designVersionRegex.find(tagWrapper.root)?.groupValues?.getOrNull(1)
                    val guidelinesBlockquote = tagWrapper.root.firstMemberOfTypeOrNull<BlockQuote> { designGuidelinesRegex.find(it) != null }
                    val guidelinesAnchor = guidelinesBlockquote?.firstMemberOfTypeOrNull<A>()
                    val guidelinesText = guidelinesAnchor?.firstMemberOfTypeOrNull<Text>()?.body
                    val guidelinesHref = guidelinesAnchor?.params["href"]
                    val designTagWrapper = if (version != null && guidelinesText != null && guidelinesHref != null) {
                        getDesignTagWrapper(version, guidelinesText, guidelinesHref)
                    } else {
                        null
                    }

                    listOfNotNull(tagWrapper.copy(root = tagWrapperRoot), designTagWrapper)
                } else {
                    listOf(tagWrapper)
                }
            }
            node.copy(children = nodeChildren)
        }
    }

    private fun getDesignTagWrapper(version: String, guidelinesText: String, guidelinesHref: String): CustomTagWrapper {
        val guidelinesBody = "<a href=\"$guidelinesHref\" class=\"external\" target=\"_blank\">$guidelinesText</a>"
        val guidelinesParams = mapOf("content-type" to "html")

        return CustomTagWrapper(
            CustomDocTag(
                listOf(
                    Table(
                        listOf(
                            Tr(
                                listOf(
                                    Td(listOf(Text("Guidelines"))),
                                    Td(listOf(Text(guidelinesBody, params = guidelinesParams)))
                                )
                            ),
                            Tr(
                                listOf(
                                    Td(listOf(Text("Version"))),
                                    Td(listOf(Text(version)))
                                )
                            )
                        )
                    )
                ),
                name = "MARKDOWN_FILE"
            ),
            OudsComponentTagContentProvider.COMPONENT_DESIGN_TAG_NAME
        )
    }

    private fun DocTag.removeDescendant(predicate: (DocTag) -> Boolean): DocTag {
        val filteredChildren = children.filterNot(predicate).map { it.removeDescendant(predicate) }
        return when (this) {
            is A -> copy(children = filteredChildren)
            is B -> copy(children = filteredChildren)
            is Big -> copy(children = filteredChildren)
            is BlockQuote -> copy(children = filteredChildren)
            Br -> this
            is Caption -> copy(children = filteredChildren)
            is Cite -> copy(children = filteredChildren)
            is CodeBlock -> copy(children = filteredChildren)
            is CodeInline -> copy(children = filteredChildren)
            is CustomDocTag -> copy(children = filteredChildren)
            is Dd -> copy(children = filteredChildren)
            is Dfn -> copy(children = filteredChildren)
            is Dir -> copy(children = filteredChildren)
            is Div -> copy(children = filteredChildren)
            is Dl -> copy(children = filteredChildren)
            is DocumentationLink -> copy(children = filteredChildren)
            is Dt -> copy(children = filteredChildren)
            is Em -> copy(children = filteredChildren)
            is Font -> copy(children = filteredChildren)
            is Footer -> copy(children = filteredChildren)
            is Frame -> copy(children = filteredChildren)
            is FrameSet -> copy(children = filteredChildren)
            is H1 -> copy(children = filteredChildren)
            is H2 -> copy(children = filteredChildren)
            is H3 -> copy(children = filteredChildren)
            is H4 -> copy(children = filteredChildren)
            is H5 -> copy(children = filteredChildren)
            is H6 -> copy(children = filteredChildren)
            is Head -> copy(children = filteredChildren)
            is Header -> copy(children = filteredChildren)
            HorizontalRule -> this
            is Html -> copy(children = filteredChildren)
            is I -> copy(children = filteredChildren)
            is IFrame -> copy(children = filteredChildren)
            is Img -> copy(children = filteredChildren)
            is Index -> copy(children = filteredChildren)
            is Input -> copy(children = filteredChildren)
            is Li -> copy(children = filteredChildren)
            is Link -> copy(children = filteredChildren)
            is Listing -> copy(children = filteredChildren)
            is Main -> copy(children = filteredChildren)
            is Menu -> copy(children = filteredChildren)
            is Meta -> copy(children = filteredChildren)
            is Nav -> copy(children = filteredChildren)
            is NoFrames -> copy(children = filteredChildren)
            is NoScript -> copy(children = filteredChildren)
            is Ol -> copy(children = filteredChildren)
            is P -> copy(children = filteredChildren)
            is Pre -> copy(children = filteredChildren)
            is Script -> copy(children = filteredChildren)
            is Section -> copy(children = filteredChildren)
            is Small -> copy(children = filteredChildren)
            is Span -> copy(children = filteredChildren)
            is Strikethrough -> copy(children = filteredChildren)
            is Strong -> copy(children = filteredChildren)
            is Sub -> copy(children = filteredChildren)
            is Sup -> copy(children = filteredChildren)
            is TBody -> copy(children = filteredChildren)
            is TFoot -> copy(children = filteredChildren)
            is THead -> copy(children = filteredChildren)
            is Table -> copy(children = filteredChildren)
            is Td -> copy(children = filteredChildren)
            is Text -> copy(children = filteredChildren)
            is Th -> copy(children = filteredChildren)
            is Title -> copy(children = filteredChildren)
            is Tr -> copy(children = filteredChildren)
            is Tt -> copy(children = filteredChildren)
            is U -> copy(children = filteredChildren)
            is Ul -> copy(children = filteredChildren)
            is Var -> copy(children = filteredChildren)
        }
    }

    private fun Regex.find(docTag: DocTag, startIndex: Int = 0): MatchResult? {
        return docTag.withDescendants()
            .filterIsInstance<Text>()
            .firstNotNullOfOrNull { find(it.body, startIndex) }
    }

    inline fun <reified T> WithChildren<WithChildren<*>>.firstMemberOfTypeOrNull(predicate: (T) -> Boolean): T? where T : WithChildren<*> {
        return withDescendants().filterIsInstance<T>().firstOrNull(predicate)
    }
}
