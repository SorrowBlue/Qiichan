package com.sorrowblue.qiichan.ui.item

import io.noties.prism4j.annotations.PrismBundle

@PrismBundle(
	include = ["brainfuck",
		"c",
		"clike",
		"clojure",
		"cpp",
		"csharp",
		"css",
		"dart",
		"git",
		"go",
		"groovy",
		"java",
		"javascript",
		"json",
		"kotlin",
		"latex",
		"makefile",
		"markdown",
		"markup",
		"python",
		"scala",
		"sql",
		"swift"],
	grammarLocatorClassName = ".MyGrammarLocator"
)
class MyHighLite {
}
