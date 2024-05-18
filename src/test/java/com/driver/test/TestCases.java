package com.driver.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCases {

    private MarketingStrategyRepository repository;

    @Before
    public void setUp() {
        repository = new MarketingStrategyRepository();
    }

    @Test
    public void testCreateAndGetStrategy() {
        String result = repository.addStrategy("Social Media Blitz", "Focus on promoting via Facebook and Instagram", "Ages 18-30", 10000, 50000);
        assertEquals("Strategy \"Social Media Blitz\" added successfully.", result);

        String fetched = repository.fetchStrategyByName("Social Media Blitz");
        assertEquals("Social Media Blitz: Focus on promoting via Facebook and Instagram for target audience Ages 18-30. Budget: $10000.00, Potential ROI: $50000.00", fetched);
    }

    @Test
    public void testUpdateStrategy() {
        repository.addStrategy("Email Campaign", "Promoting via emails", "All ages", 5000, 20000);
        String result = repository.updateStrategy("Email Campaign", "Updated email campaign description", null, null, null);
        assertEquals("Strategy \"Email Campaign\" updated successfully.", result);

        String fetched = repository.fetchStrategyByName("Email Campaign");
        assertEquals("Email Campaign: Updated email campaign description for target audience All ages. Budget: $5000.00, Potential ROI: $20000.00", fetched);
    }
}
