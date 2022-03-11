package com.example.mvvm_design_pattern.viewmodel

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mvvm_design_pattern.database.UserDB
import com.example.mvvm_design_pattern.model.*
import com.example.mvvm_design_pattern.network.Resource
import com.example.mvvm_design_pattern.repository.UserRepository
import junit.framework.TestCase
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception


@RunWith(AndroidJUnit4::class)
class UserViewModelTest: TestCase() {

    private lateinit var userDatabase: UserDB
    private lateinit var viewModel: UserViewModel
    private lateinit var userRepository: UserRepository


    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userDatabase = Room.inMemoryDatabaseBuilder(
            context, UserDB::class.java
        ).allowMainThreadQueries().build()
        userRepository = UserRepository(userDatabase)
        viewModel = UserViewModel(userRepository,ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testNull() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                assertNotNull(viewModel.getUserList(20))
            }
        }

    }

    @Test
    fun testAPI() {
        // api calling
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                viewModel.getUserList(20)
                delay(3000)
                val mData = (viewModel.usersListLiveData.value as Resource.Success).data
                // api answer
                val account_id = mData.items[0].account_id
                Log.e("test", "(API) account_id: $account_id")
                assertEquals(account_id,11683 )
            }
        }

    }

    @Test
    fun testDatabase() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                // call to UI thread
                val list = demoData()
                val apiResponse = ApiResponse(has_more = false, quota_max = 200, quota_remaining = 10, items = list)
                viewModel.saveDataToDb(apiResponse)
                // database api calling
                viewModel.gettingDataFromDatabase(Exception())
                delay(3000)
                val mData = (viewModel.usersListLiveData.value as Resource.Success).data
                // api answer
                val account_id = mData.items[0].account_id
                Log.e("test", "(DB) account_id: $account_id")
                assertEquals(account_id,100 )
            }
        }



    }

    private fun demoData(): ArrayList<Item> {
        val externalLink = ExternalLink(link = "", type = "")
        val externalList = ArrayList<ExternalLink>()
        externalList.add(externalLink)
        val tagList = ArrayList<String>()
        tagList.add("tag1")
        val collectiveItem = Collective(
            collective = CollectiveX(
                description = "",
                external_links = externalList,
                link = "",
                name = "",
                tags = tagList,
                slug = ""
            ),
            role = ""
        )

        val collectiveList = ArrayList<Collective>()
        collectiveList.add(collectiveItem)

        val item = Item(
            account_id = 100,
            accept_rate = 100,
            website_url = "",
            user_type = "",
            user_id = 123,
            reputation = 1000,
            reputation_change_day = 123456,
            reputation_change_month = 12345,
            reputation_change_quarter = 12,
            reputation_change_week = 32,
            reputation_change_year = 2026,
            profile_image = "",
            location = "",
            link = "",
            last_access_date = 123,
            last_modified_date = 321,
            is_employee = true,
            display_name = "",
            creation_date = 123456,
            badge_counts = BadgeCounts(100, 200, 100),
            collectives = collectiveList
        )

        val list = ArrayList<Item>()
        list.add(item)
        return list
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        userDatabase.close()
    }

}