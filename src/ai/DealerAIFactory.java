package ai;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * Factory to construct DealerAI. Reads configuration file to determine at runtime which AI to use - loads appropriate class.
 *
 */
public class DealerAIFactory {

	private Properties aiType;
	private DealerAI dealerAI = null;
	private static DealerAIFactory me = null;

	private DealerAIFactory(){
		aiType = new Properties();
		try {
			aiType.load(new FileInputStream("properties/dealerAI.properties"));
			String aiClass = "ai." + aiType.getProperty("AI");
			dealerAI = (DealerAI) Class.forName(aiClass).newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	static{
		me = new DealerAIFactory();
	}
	
	public static DealerAIFactory getMe(){
		return me;
	}
	
	public DealerAI getDealerAI(){
		return dealerAI;
	}
}
