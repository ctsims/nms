package org.motechproject.nms.api.osgi;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.nms.api.web.contract.LanguageRequest;
import org.motechproject.nms.flw.domain.FrontLineWorker;
import org.motechproject.nms.flw.domain.Service;
import org.motechproject.nms.flw.domain.ServiceUsage;
import org.motechproject.nms.flw.domain.ServiceUsageCap;
import org.motechproject.nms.flw.domain.WhitelistEntry;
import org.motechproject.nms.flw.repository.FrontLineWorkerDataService;
import org.motechproject.nms.flw.repository.ServiceUsageCapDataService;
import org.motechproject.nms.flw.repository.ServiceUsageDataService;
import org.motechproject.nms.flw.repository.WhitelistEntryDataService;
import org.motechproject.nms.flw.service.FrontLineWorkerService;
import org.motechproject.nms.kilkari.domain.Subscriber;
import org.motechproject.nms.kilkari.domain.Subscription;
import org.motechproject.nms.kilkari.domain.SubscriptionPack;
import org.motechproject.nms.kilkari.repository.SubscriberDataService;
import org.motechproject.nms.kilkari.repository.SubscriptionDataService;
import org.motechproject.nms.kilkari.repository.SubscriptionPackDataService;
import org.motechproject.nms.kilkari.service.KilkariService;
import org.motechproject.nms.language.domain.Language;
import org.motechproject.nms.language.domain.CircleLanguage;
import org.motechproject.nms.language.repository.CircleLanguageDataService;
import org.motechproject.nms.language.repository.LanguageDataService;
import org.motechproject.nms.location.domain.District;
import org.motechproject.nms.location.domain.State;
import org.motechproject.nms.location.repository.StateDataService;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.motechproject.testing.osgi.http.SimpleHttpClient;
import org.motechproject.testing.utils.TestContext;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Verify that User API is present and functional.
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class UserControllerBundleIT extends BasePaxIT {
    private static final String ADMIN_USERNAME = "motech";
    private static final String ADMIN_PASSWORD = "motech";

    @Inject
    private KilkariService kilkariService;

    @Inject
    private SubscriberDataService subscriberDataService;

    @Inject
    private SubscriptionPackDataService subscriptionPackDataService;

    @Inject
    private SubscriptionDataService subscriptionDataService;

    @Inject
    private FrontLineWorkerService frontLineWorkerService;

    @Inject
    private FrontLineWorkerDataService frontLineWorkerDataService;

    @Inject
    private ServiceUsageDataService serviceUsageDataService;

    @Inject
    private ServiceUsageCapDataService serviceUsageCapDataService;

    @Inject
    private LanguageDataService languageDataService;

    @Inject
    private CircleLanguageDataService circleLanguageDataService;

    @Inject
    private StateDataService stateDataService;

    @Inject
    private WhitelistEntryDataService whitelistEntryDataService;

    // TODO: Clean up data creation and cleanup
    private void cleanAllData() {
        whitelistEntryDataService.deleteAll();
        stateDataService.deleteAll();
        subscriptionDataService.deleteAll();
        subscriptionPackDataService.deleteAll();
        subscriberDataService.deleteAll();
        serviceUsageCapDataService.deleteAll();
        serviceUsageDataService.deleteAll();
        frontLineWorkerDataService.deleteAll();
        circleLanguageDataService.deleteAll();
        languageDataService.deleteAll();
    }

    /*
    Creates two subscription packs ('pack1' and 'pack2')
    Create two subscribers:
        Subscriber 1000000000L is subscribed to pack 'pack1'
        Subscriber 2000000000L is subscribed to packs 'pack1' and 'pack2'
     */
    private void createKilkariTestData() {
        cleanAllData();

        Language ta = languageDataService.create(new Language("tamil", "50"));
        SubscriptionPack pack1 = subscriptionPackDataService.create(new SubscriptionPack("pack1"));
        SubscriptionPack pack2 = subscriptionPackDataService.create(new SubscriptionPack("pack2"));
        List<SubscriptionPack> onePack = Arrays.asList(pack1);
        List<SubscriptionPack> twoPacks = Arrays.asList(pack1, pack2);

        Subscriber subscriber1 = subscriberDataService.create(new Subscriber(1000000000L));
        Subscriber subscriber2 = subscriberDataService.create(new Subscriber(2000000000L));

        Subscription subscription1 = subscriptionDataService.create(new Subscription(subscriber1, pack1, ta));
        Subscription subscription2 = subscriptionDataService.create(new Subscription(subscriber2, pack1, ta));
        Subscription subscription3 = subscriptionDataService.create(new Subscription(subscriber2, pack2, ta));
    }

    private void createFlwCappedServiceNoUsageNoLocationNoLanguage() {
        cleanAllData();

        FrontLineWorker flw = new FrontLineWorker("Frank Lloyd Wright", 1111111111L);
        frontLineWorkerService.add(flw);

        Language language = new Language("Papiamento", "99");
        languageDataService.create(language);

        CircleLanguage circleLanguage = new CircleLanguage("AA", language);
        circleLanguageDataService.create(circleLanguage);

        ServiceUsageCap serviceUsageCap = new ServiceUsageCap(null, Service.MOBILE_KUNJI, 3600);
        serviceUsageCapDataService.create(serviceUsageCap);
    }

    private void createFlwWithLanguageServiceUsageAndCappedService() {
        cleanAllData();

        Language language = new Language("English", "10");
        languageDataService.create(language);

        FrontLineWorker flw = new FrontLineWorker("Frank Llyod Wright", 1111111111L);
        flw.setLanguage(language);
        frontLineWorkerService.add(flw);

        language = new Language("Papiamento", "99");
        languageDataService.create(language);

        CircleLanguage circleLanguage = new CircleLanguage("AA", language);
        circleLanguageDataService.create(circleLanguage);

        ServiceUsageCap serviceUsageCap = new ServiceUsageCap(null, Service.MOBILE_KUNJI, 3600);
        serviceUsageCapDataService.create(serviceUsageCap);

        // A service record without endOfService and WelcomePrompt played
        ServiceUsage serviceUsage = new ServiceUsage(flw, Service.MOBILE_KUNJI, 1, 0, 0, DateTime.now());
        serviceUsageDataService.create(serviceUsage);
    }

    private void createFlwWithLanguageFullServiceUsageAndCappedService() {
        cleanAllData();

        Language language = new Language("English", "10");
        languageDataService.create(language);

        FrontLineWorker flw = new FrontLineWorker("Frank Llyod Wright", 1111111111L);
        flw.setLanguage(language);
        frontLineWorkerService.add(flw);

        language = new Language("Papiamento", "99");
        languageDataService.create(language);

        CircleLanguage circleLanguage = new CircleLanguage("AA", language);
        circleLanguageDataService.create(circleLanguage);

        ServiceUsageCap serviceUsageCap = new ServiceUsageCap(null, Service.MOBILE_KUNJI, 3600);
        serviceUsageCapDataService.create(serviceUsageCap);

        ServiceUsage serviceUsage = new ServiceUsage(flw, Service.MOBILE_KUNJI, 1, 1, 1, DateTime.now());
        serviceUsageDataService.create(serviceUsage);
    }

    private void createFlwWithLanguageFullUsageOfBothServiceUncapped() {
        cleanAllData();

        Language language = new Language("English", "10");
        languageDataService.create(language);

        FrontLineWorker flw = new FrontLineWorker("Frank Llyod Wright", 1111111111L);
        flw.setLanguage(language);
        frontLineWorkerService.add(flw);

        language = new Language("Papiamento", "99");
        languageDataService.create(language);

        CircleLanguage circleLanguage = new CircleLanguage("AA", language);
        circleLanguageDataService.create(circleLanguage);

        ServiceUsage serviceUsage = new ServiceUsage(flw, Service.MOBILE_KUNJI, 1, 1, 1, DateTime.now());
        serviceUsageDataService.create(serviceUsage);

        // Academy doesn't have a welcome prompt
        serviceUsage = new ServiceUsage(flw, Service.MOBILE_ACADEMY, 1, 1, 0, DateTime.now());
        serviceUsageDataService.create(serviceUsage);

        ServiceUsageCap serviceUsageCap = new ServiceUsageCap(null, Service.MOBILE_KUNJI, 10);
        serviceUsageCapDataService.create(serviceUsageCap);
    }

    private void createFlwWithStateNotInWhitelist() {
        cleanAllData();

        District district = new District();
        district.setName("9");
        district.setDistrictCode(9l);

        // Currently the whitelist code has the config for state based whitelisting hardcoded.
        // There is a todo and ticket tracking that work.  By default the state named 'Whitelist' has
        // whitelisting turned on.
        State whitelist = new State("Whitelist", 1l);
        whitelist.getDistricts().add(district);
        stateDataService.create(whitelist);

        WhitelistEntry entry = new WhitelistEntry(0000000000l, whitelist);
        whitelistEntryDataService.create(entry);

        FrontLineWorker flw = new FrontLineWorker("Frank Llyod Wright", 1111111111l);
        flw.setDistrict(district);
        frontLineWorkerService.add(flw);
    }

    private void createFlwWithLanguageLocationCodeNotInWhitelist() {
        cleanAllData();

        // Currently the code to get a state from a languageLocationCode is stubbed out.
        // llc 34 returns the state "Whitelist".  There is a todo tracking this.
        Language language = new Language("Language From Whitelisted State", "34");
        languageDataService.create(language);

        State whitelist = new State("Whitelist", 1l);
        stateDataService.create(whitelist);

        WhitelistEntry entry = new WhitelistEntry(0000000000l, whitelist);
        whitelistEntryDataService.create(entry);

        FrontLineWorker flw = new FrontLineWorker("Frank Llyod Wright", 1111111111l);
        flw.setLanguage(language);
        frontLineWorkerService.add(flw);
    }

    private void createCircleWithLanguage() {
        cleanAllData();
        Language language = new Language("Papiamento", "99");
        languageDataService.create(language);

        CircleLanguage circleLanguage = new CircleLanguage("AA", language);
        circleLanguageDataService.create(circleLanguage);
    }

    @Test
    public void testKilkariUserRequest() throws IOException, InterruptedException {
        createKilkariTestData();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?callingNumber=2000000000&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
                "{\"languageLocationCode\":null,\"defaultLanguageLocationCode\":null,\"subscriptionPackList\":[\"pack2\",\"pack1\"]}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testFlwUserRequestWithoutServiceUsage() throws IOException, InterruptedException {
        createFlwCappedServiceNoUsageNoLocationNoLanguage();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
            "{\"languageLocationCode\":null,\"defaultLanguageLocationCode\":\"99\",\"currentUsageInPulses\":0,\"endOfUsagePromptCounter\":0,\"welcomePromptFlag\":false,\"maxAllowedUsageInPulses\":3600,\"maxAllowedEndOfUsagePrompt\":2}",
            ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testFlwUserRequestWithServiceUsageOnly() throws IOException, InterruptedException {
        createFlwWithLanguageServiceUsageAndCappedService();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
            "{\"languageLocationCode\":\"10\",\"defaultLanguageLocationCode\":\"99\",\"currentUsageInPulses\":1,\"endOfUsagePromptCounter\":0,\"welcomePromptFlag\":false,\"maxAllowedUsageInPulses\":3600,\"maxAllowedEndOfUsagePrompt\":2}",
            ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testFlwUserRequestWithServiceUsageAndEndOfUsageAndWelcomeMsg() throws IOException, InterruptedException {
        createFlwWithLanguageFullServiceUsageAndCappedService();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
                "{\"languageLocationCode\":\"10\",\"defaultLanguageLocationCode\":\"99\",\"currentUsageInPulses\":1,\"endOfUsagePromptCounter\":1,\"welcomePromptFlag\":true,\"maxAllowedUsageInPulses\":3600,\"maxAllowedEndOfUsagePrompt\":2}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testInvalidServiceName() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/NO_SERVICE/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        //todo: replace with execHttpRequest method that also tests response body (in addition to status code)
        //todo: when it's available in platform: org.motechproject.testing.osgi.http.SimpleHttpClient
        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_BAD_REQUEST,
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testNoCallingNumber() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<callingNumber: Not Present>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testInvalidCallingNumber() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?callingNumber=123&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<callingNumber: Invalid>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testNoOperator() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?callingNumber=1111111111&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<operator: Not Present>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testNoCircle() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?callingNumber=1111111111&operator=OP&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<circle: Not Present>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testNoCallId() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?callingNumber=1111111111&operator=OP&circle=AA", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<callId: Not Present>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    @Ignore //todo: #60 figure out an elegant way to test that
    public void testInternalError() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/kilkari/user?callingNumber=1111111111&operator=OP&circle=AA", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_INTERNAL_SERVER_ERROR,
                "{\"failureReason\":\"Internal Error\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    // An FLW that does not exist
    @Test
    public void testGetUserDetailsUnknownUser() throws IOException, InterruptedException {
        createCircleWithLanguage();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111112&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
                "{\"languageLocationCode\":null,\"defaultLanguageLocationCode\":\"99\",\"currentUsageInPulses\":0,\"endOfUsagePromptCounter\":0,\"welcomePromptFlag\":false,\"maxAllowedUsageInPulses\":-1,\"maxAllowedEndOfUsagePrompt\":2}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    // An FLW with usage for both MA and MK
    @Test
    public void testGetUserDetailsUserOfBothServices() throws IOException, InterruptedException {
        createFlwWithLanguageFullUsageOfBothServiceUncapped();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobileacademy/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
                "{\"languageLocationCode\":\"10\",\"defaultLanguageLocationCode\":\"99\",\"currentUsageInPulses\":1,\"endOfUsagePromptCounter\":1,\"welcomePromptFlag\":false,\"maxAllowedUsageInPulses\":-1,\"maxAllowedEndOfUsagePrompt\":2}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    // An FLW with usage and a service with a cap
    @Test
    public void testGetUserDetailsServiceCapped() throws IOException, InterruptedException {
        createFlwWithLanguageFullUsageOfBothServiceUncapped();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet,
                "{\"languageLocationCode\":\"10\",\"defaultLanguageLocationCode\":\"99\",\"currentUsageInPulses\":1,\"endOfUsagePromptCounter\":1,\"welcomePromptFlag\":true,\"maxAllowedUsageInPulses\":10,\"maxAllowedEndOfUsagePrompt\":2}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testGetUserNotInWhitelistedByState() throws IOException, InterruptedException {
        createFlwWithStateNotInWhitelist();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_FORBIDDEN,
                "{\"failureReason\":\"<callingNumber: Not Authorized>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testGetUserNotInWhitelistedByLanguageLocationCode() throws IOException, InterruptedException {
        createFlwWithLanguageLocationCodeNotInWhitelist();

        HttpGet httpGet = new HttpGet(String.format("http://localhost:%d/api/mobilekunji/user?callingNumber=1111111111&operator=OP&circle=AA&callId=123456789012345", TestContext.getJettyPort()));

        assertTrue(SimpleHttpClient.execHttpRequest(httpGet, HttpStatus.SC_FORBIDDEN,
                "{\"failureReason\":\"<callingNumber: Not Authorized>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageInvalidService() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/NO_SERVICE/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"callId\":123456789012345,\"languageLocationCode\":\"10\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");
        
        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<serviceName: Invalid>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageMissingCallingNumber() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callId\":123456789012345,\"languageLocationCode\":\"10\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<callingNumber: Not Present>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageInvalidCallingNumber() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));

        LanguageRequest request = new LanguageRequest(
                1L, //callingNumber
                123456789012345L, //callId
                "123"); //languageLocationCode
        String json = new ObjectMapper().writeValueAsString(request);
        StringEntity params = new StringEntity(json);
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<callingNumber: Invalid>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageMissingCallId() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(
                String.format("http://localhost:%d/api/mobilekunji/languageLocationCode",
                        TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"languageLocationCode\":\"10\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST,
                "{\"failureReason\":\"<callId: Not Present>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageInvalidCallId() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(
                String.format("http://localhost:%d/api/mobilekunji/languageLocationCode",
                        TestContext.getJettyPort()));
        StringEntity params = new StringEntity(
                "{\"callingNumber\":1111111111,\"callId\":\"12345678901234a\",\"languageLocationCode\":\"10\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST, ADMIN_USERNAME,
                ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageMissingLanguageLocationCode() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"callId\":123456789012345}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST, ADMIN_USERNAME,
                ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageInvalidLanguageLocationCode() throws IOException, InterruptedException {
        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"callId\":123456789012345,\"languageLocationCode\":null}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_BAD_REQUEST, ADMIN_USERNAME,
                ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageNoFLW() throws IOException, InterruptedException {
        createCircleWithLanguage();

        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"callId\":123456789012345,\"languageLocationCode\":\"99\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_OK));

        FrontLineWorker flw = frontLineWorkerService.getByContactNumber(1111111111l);
        assertNotNull(flw);
        Language language = flw.getLanguage();
        assertNotNull(language);
        assertEquals("FLW Language Code", "99", language.getCode());
    }

    @Test
    public void testSetLanguageLanguageNotFound() throws IOException, InterruptedException {
        createFlwCappedServiceNoUsageNoLocationNoLanguage();

        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"callId\":123456789012345,\"languageLocationCode\":\"77\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_NOT_FOUND,
                "{\"failureReason\":\"<languageLocationCode: Not Found>\"}",
                ADMIN_USERNAME, ADMIN_PASSWORD));
    }

    @Test
    public void testSetLanguageValid() throws IOException, InterruptedException {
        createFlwCappedServiceNoUsageNoLocationNoLanguage();

        HttpPost httpPost = new HttpPost(String.format("http://localhost:%d/api/mobilekunji/languageLocationCode", TestContext.getJettyPort()));
        StringEntity params = new StringEntity("{\"callingNumber\":1111111111,\"callId\":123456789012345,\"languageLocationCode\":\"99\"}");
        httpPost.setEntity(params);

        httpPost.addHeader("content-type", "application/json");

        assertTrue(SimpleHttpClient.execHttpRequest(httpPost, HttpStatus.SC_OK));

        FrontLineWorker flw = frontLineWorkerService.getByContactNumber(1111111111L);
        Language language = flw.getLanguage();
        assertNotNull(language);
        assertEquals("FLW Language Code", "99", language.getCode());
    }
}