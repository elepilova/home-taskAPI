package org.example.tests;

import org.example.clients.RestAssuredClient;
import org.example.steps.TestSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static RestAssuredClient restAssuredClient;
    protected TestSteps steps;

    protected String key = "56e5204a7c8894a830b3031ade720921";
    protected String token = "ATTAb335153726e335290a59fb27718706ed935831483b86b46f2a1b33d0970fa4951ADF38F3";

    @BeforeSuite
    public void globalSetup() {
        restAssuredClient = new RestAssuredClient(key, token);
    }

    @BeforeClass
    public void setUp() {
        steps = new TestSteps();
    }
}