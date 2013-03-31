package ai;

import java.io.FileInputStream;
import java.util.Properties;

public class DealerAIFactory {

	private Properties aiType;
	private DealerAI dealerAI = null;
	private static DealerAIFactory me = null;

	//MAKE THIS PRIVATE WHEN YOU'RE DONE WITH IT
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
