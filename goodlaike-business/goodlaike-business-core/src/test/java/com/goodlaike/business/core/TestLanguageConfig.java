package com.goodlaike.business.core;

import org.junit.Test;

import com.goodlaike.business.core.support.LanguageStore;

/**
 * 
 * @author jail
 */
public class TestLanguageConfig extends BaseTest{
    
    @Test
    public void getLanguagesWithOutDefault(){
        System.out.println(LanguageStore.getLanguagesWithOutDefault());
    }
    
    @Test
    public void getLanguages(){
        System.out.println(LanguageStore.getLanguages());
    }
    
    @Test
    public void getLanguage(){
        System.out.println(LanguageStore.getLanguage("en"));
    }
}
