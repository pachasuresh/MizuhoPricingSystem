
  package com.mizuho.mizuhohomeproject;
  
  import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import
  org.springframework.boot.test.context.SpringBootTest;
  
  @SpringBootTest class MizuhoHomeProjectApplicationTests {
  
  @Mock
  MizuhoHomeProjectApplication mizuhoHomeProjectApplication;
	  
  @Test void run()throws Exception { 
	  MizuhoHomeProjectApplication.main(new String[] {});
   }
  }
 