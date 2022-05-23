package io.github.Adam;



import io.github.Adam.Hello.HelloService;
import io.github.Adam.Lang.Lang;
import io.github.Adam.Lang.LangRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class Test {
    // System Under Test
    private static final String WELCOME = "Hello ";
    private static final String FALLBACK_ID_WELCOME = "Hola";

    //null name
    @org.junit.Test
    public void test_nullName_prepareGreeting_returnsGreetingWithFallbackName()  {

        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService();
        //when
        var result = SUT.prepareGreeting(null, -1);
        //then
        assertEquals(WELCOME + HelloService.FALLBACK_NAME + "!", result);
    }

    //test name
    @org.junit.Test
    public void test_name_prepareGreeting_name_returnsGreetingWithName() {
        //given+when
        var SUT = new HelloService();
        var name = "test";
        var result = SUT.prepareGreeting(name, -1);
        //then
        assertEquals(WELCOME + name + "!", result);
    }


    @org.junit.Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        HelloService.FALLBACK_LANG.getWelcomeMsg();
        //
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);

        //when
        var name = "test";

        //then
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @org.junit.Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        //given+when
        //zalokowana wersja
        var fallbackIdWelcome = "Hola";
        var mockRepository = fallbackLangIdRepository();

        var SUT = new HelloService(mockRepository);

        //when
        var name = "test";

        //then
        var result = SUT.prepareGreeting(null, null);
        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals((HelloService.FALLBACK_LANG.getId()))) {
                    return Optional.of((new Lang(null, FALLBACK_ID_WELCOME, null)));
                }
                return Optional.empty();
            }
        };
    }


//We ensure that the repo will behave as expected, and any errors are due only to our tested function.
    //override findById
    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}