package parsefile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.Test;
import org.junit.gen5.api.Assertions;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.Mock;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;
import org.powermock.reflect.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;



@RunWith(PowerMockRunner.class)
@PrepareForTest(ParseFile.class)

public class ParseFileTest  {
	
	
	@Test
    public void shouldMockBufferedReaderConstructorAndReadMoreLines() throws Exception {
        // given
		ParseFile parser = new ParseFile("fake");
	    PowerMockito.mockStatic(Files.class);


        BufferedReader readerMock = PowerMockito.mock(BufferedReader.class);
        
     // when called new BufferedReader(Reader) return mock
        Constructor<BufferedReader> constructor = Whitebox.getConstructor(
                BufferedReader.class, Reader.class);
        PowerMockito.whenNew(constructor)//
                .withArguments(Mockito.any(Reader.class))//
                .thenReturn(readerMock);
        
        when( Files.newBufferedReader(any())).thenReturn(readerMock);  
        // return more lines
        when(readerMock.readLine()).thenReturn("1544988780 quark garak",//
                "1544988782 quark garak",//
                "1544988784 bfa quark",//
                "1544988790 qark garak").thenReturn(null);

        

        // when
        String result = parser.printConnectionReport("quark","16-12-2018 19:33:33");

        // then,
        Assertions.assertEquals("bfa***garak", result);
    }

}
