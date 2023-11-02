package com.bot.telegram.service.filters

import com.bot.telegram.repository.WordRepository
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.ru.RussianAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.TextField
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.index.Term
import org.apache.lucene.search.*
import org.apache.lucene.search.BooleanQuery.Builder
import org.apache.lucene.store.ByteBuffersDirectory
import org.springframework.stereotype.Service
import org.tartarus.snowball.ext.RussianStemmer
import java.util.*


@Service
class WordFilter(private val wordRepository: WordRepository) {
    private val index: ByteBuffersDirectory
    private val analyzer: Analyzer = RussianAnalyzer()

    init {
        val wordsFromDatabase = wordRepository.findAll().map { it.word ?: "" }.toSet()
        val normalizedWords = normalizeWords(wordsFromDatabase)
        index = createIndex(normalizedWords)
    }

    private fun normalizeWords(words: Set<String>): Set<String> {
        val stemmer = RussianStemmer()
        val normalizedWords = mutableSetOf<String>()
        for (word in words) {
            stemmer.current = word
            stemmer.stem()
            val normalizedWord = stemmer.current.lowercase(Locale.getDefault())
            normalizedWords.add(normalizedWord)
        }
        return normalizedWords
    }

    private fun createIndex(normalizedWords: Set<String>): ByteBuffersDirectory {
        val index = ByteBuffersDirectory()
        val config = IndexWriterConfig(analyzer)
        val writer = IndexWriter(index, config)

        for (word in normalizedWords) {
            val doc = Document()
            doc.add(TextField("content", word, Field.Store.YES))
            writer.addDocument(doc)
        }

        writer.close()
        return index
    }

    fun containsPhrase(input: String): Boolean {
        val normalizedInput = input.lowercase(Locale.getDefault())
        val query = createPhraseQuery(normalizedInput)
        return executeQuery(query)
    }

    private fun createPhraseQuery(input: String): Query {
        val stemmer = RussianStemmer()
        val words = input.split("[.,;:!?\\-\\s]+".toRegex())
        val builder = Builder()
        for (word in words) {
            stemmer.current = word
            stemmer.stem()
            val normalizedWord = stemmer.current
            val termQuery = TermQuery(Term("content", normalizedWord))
            builder.add(BoostQuery(termQuery, 1.0f), BooleanClause.Occur.SHOULD)
        }
        return builder.build()
    }

    private fun executeQuery(query: Query): Boolean {
        val reader = DirectoryReader.open(index)
        val searcher = IndexSearcher(reader)
        val topDocs: TopDocs = searcher.search(query, 1)
        return topDocs.totalHits.value > 0
    }
}