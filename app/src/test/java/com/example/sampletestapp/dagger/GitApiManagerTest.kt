package com.example.sampletestapp.dagger.com.example.sampletestapp.dagger

import com.example.sampletestapp.dagger.*
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GitApiManagerTest {
    private lateinit var subject: GitApiManager
    private val testApi: TestApi = mockk()

    @Before
    fun setUp() {
        subject = GitApiManager(testApi)
    }

    @Test
    fun testFetchCommitDetails_apiReturnsSuccess_verfiyItems() {
        val commitDetails: CommitDetails = mockk()
        val author: Author = mockk()
        val commit: Commit = mockk()
        every { commitDetails.sha } returns "sha123"
        every { commitDetails.commit } returns commit
        every { commit.message } returns "Initial commit"
        every { commit.author } returns author
        every { author.name } returns "Harjot"
        val list: List<CommitDetails> = listOf(commitDetails)
        every { testApi.fetchCommitDetails() } returns Single.just(list)

        val testObserver = subject.fetchCommitDetails().test()

        testObserver.assertComplete()
        val items = testObserver.values()[0].list
        Assert.assertEquals("Harjot", items[0].author)
        Assert.assertEquals("Initial commit", items[0].message)
        Assert.assertEquals("sha123", items[0].sha)
    }

    @Test
    fun testFetchCommitDetails_apiReturnsError_verfiyError() {
        val error: Throwable = mockk()
        every { testApi.fetchCommitDetails() } returns Single.error(error)

        val testObserver = subject.fetchCommitDetails().test()

        testObserver.assertError(error)
    }
}