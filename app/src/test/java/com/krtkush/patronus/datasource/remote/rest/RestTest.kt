package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.datasource.di.NetworkModule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

class RestTest {

    private lateinit var retrofit: Retrofit
    private lateinit var apiHelperImpl: ApiHelperImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userListResponseMock: MockResponse

    private val userListJsonString = "" +
            "{\"customers\":[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-7890\",\"imageUrl\":\"https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8\",\"stickers\":[\"Fam\"]}," +
            "{\"id\":2,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-456-7891\",\"imageUrl\":\"https://fastly.picsum.photos/id/445/400/400.jpg?hmac=CCjqlZXQQ_5kl0X6naMjQKUWSbQloDYImyB9zGFOA8M\",\"stickers\":[\"Fam\",\"Ban\"]}," +
            "{\"id\":3,\"firstName\":\"Bob\",\"lastName\":\"Smith\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-7892\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":4,\"firstName\":\"Sally\",\"lastName\":\"Johnson\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-456-7893\",\"imageUrl\":\"https://fastly.picsum.photos/id/13/500/500.jpg?hmac=oIMjG56df3J3cWXHmJTmMSVj1huozLkKwY4NAUXpxOw\",\"stickers\":[\"Fam\"]},{\"id\":5,\"firstName\":\"Mark\",\"lastName\":\"Davis\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-7894\",\"imageUrl\":null,\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":6,\"firstName\":\"Emily\",\"lastName\":\"Brown\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-456-7895\",\"imageUrl\":\"https://fastly.picsum.photos/id/628/400/400.jpg?hmac=uQnrWHhKS3XBUnJaTHJHb7TBhQX7uZ0p1q_Y2hLnEWY\",\"stickers\":[\"Fam\"]},{\"id\":7,\"firstName\":\"David\",\"lastName\":\"Wilson\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-7896\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":8,\"firstName\":\"Olivia\",\"lastName\":\"Garcia\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-456-7897\",\"imageUrl\":\"https://fastly.picsum.photos/id/650/400/400.jpg?hmac=aRSRZFa8j3OPfCyAkMVThCXyRqSK-GhyPHmkcZscOC8\",\"stickers\":[\"Fam\"]},{\"id\":9,\"firstName\":\"Daniel\",\"lastName\":\"Martinez\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-5910\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":10,\"firstName\":\"Sophia\",\"lastName\":\"Lopez\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-456-1212\",\"imageUrl\":\"https://example.com/sophia.jpg\",\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":11,\"firstName\":\"Dave\",\"lastName\":\"Thompson\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-3322\",\"imageUrl\":\"https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U\",\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":12,\"firstName\":\"Federico\",\"lastName\":\"Santana\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-7789\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":13,\"firstName\":\"Joell\",\"lastName\":\"Ortiz\",\"gender\":\"MALE\",\"phoneNumber\":\"123-456-5555\",\"imageUrl\":null,\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":14,\"firstName\":\"Mery\",\"lastName\":\"Stan\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-456-4244\",\"imageUrl\":null,\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":15,\"firstName\":\"Daniel\",\"lastName\":\"Wood\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-313-7898\",\"imageUrl\":null,\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":16,\"firstName\":\"Daniel\",\"lastName\":\"Forrest\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-313-3132\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":17,\"firstName\":\"Anna\",\"lastName\":\"Rose\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-999-5555\",\"imageUrl\":null,\"stickers\":[\"Fam\",\"Ban\"]},{\"id\":18,\"firstName\":\"Jessica\",\"lastName\":\"Bangladesh\",\"gender\":\"FEMALE\",\"phoneNumber\":\"123-313-4589\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":19,\"firstName\":\"Michael\",\"lastName\":\"Johnson\",\"gender\":\"MALE\",\"phoneNumber\":\"123-987-4241\",\"imageUrl\":null,\"stickers\":[\"Ban\"]},{\"id\":20,\"firstName\":\"John\",\"lastName\":\"Michael\",\"gender\":\"MALE\",\"phoneNumber\":\"123-313-6665\",\"imageUrl\":null,\"stickers\":[\"Ban\"]}]}"

    private val userDetailsString = "{\"id\":4,\"imageUrl\":\"https://fastly.picsum.photos/id/13/500/500.jpg?hmac=oIMjG56df3J3cWXHmJTmMSVj1huozLkKwY4NAUXpxOw\",\"currentLatitude\":37.7749,\"currentLongitude\":-122.4194,\"firstName\":\"Sally\",\"lastName\":\"Johnson\",\"stickers\":[\"Ban\"],\"gender\":\"MALE\",\"phoneNumber\":\"123-456-7893\",\"address\":{\"street\":\"123MainSt\",\"city\":\"SanFrancisco\",\"state\":\"CA\",\"zip\":\"94111\",\"country\":\"USA\"}}"
    @Before
    fun setup() {

        // Mock server
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Retrofit
        retrofit = NetworkModule.provideRetrofit(
            NetworkModule.provideOkHttpClient(NetworkModule.provideHTTPLoggingInterceptor()),
            mockWebServer.url("/").toString())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testRetrofitResponseForGetUserList() = runBlocking{

        // Mock Response
        userListResponseMock = MockResponse()
            .setResponseCode(200)
            .setBody(userListJsonString)

        mockWebServer.enqueue(userListResponseMock)
        apiHelperImpl = ApiHelperImpl(NetworkModule.provideApiService(retrofit))

        val res = apiHelperImpl.getUsersList()

        assertEquals(200, res.code())
        assertEquals(20, res.body()?.customers?.size )
        assertEquals("John", res.body()?.customers?.get(0)?.firstName)
    }

    @Test
    fun testRetrofitResponseForGetUserDetails() = runBlocking{

        // Mock Response
        userListResponseMock = MockResponse()
            .setResponseCode(200)
            .setBody(userDetailsString)

        mockWebServer.enqueue(userListResponseMock)
        apiHelperImpl = ApiHelperImpl(NetworkModule.provideApiService(retrofit))

        val res = apiHelperImpl.getUserDetails(4)

        assertEquals(200, res.code())
        assertEquals("Sally", res.body()?.firstName)
    }
}