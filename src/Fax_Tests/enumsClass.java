package Fax_Tests;

public class enumsClass {
		
	// Select types
	public enum selectTypes      { INDEX, NAME, GIVEN_TEXT;}
	  	
	// Log modes
	public enum logModes {
		 	 
		MAJOR(""), NORMAL(" "), MINOR("  "), DEBUG("   ");  		 
		private String level = "";
		
		private logModes(String level) {		 
			this.level = level;   	 
		}
		public String getLevel() {   		 
			return level;    
		}
	 }
	
	// Menu paths
	public enum menuNames {
		
		FAX_IN_SETTINGS			,
		FAX_IN_SETTINGS_OPEN	,
		FAX_OUT_SETTINGS		,
		GENERAL_SETTINGS		,
		GENERAL_SETTINGS_OPEN	,
		MNG_GENERAL_SECTION		,
		FAX_IN_NUMBERS			,
		FAX_IN_NUMBERS_OPEN		,
		FAX_OUT_NUMBERS			,
		FAX_OUT_NUMBERS_OPEN	,
		GATEWAYS				,
		OUTGOING_RULES			,
		APPLICATION_LOGS		,
		APPLICATION_LOGS_OPEN	,
		RECEIVED_FAXES			,
		SENT_FAXES				,
		SENT_FAXES_OPEN			,
		ADMIN_USER_MANUALS;	
	}
	
	// Application logs sub-menus names
	public enum appLogsMenuNames {
		
		FAX_IN_SERVICE,
		FAX_OUT_SERVICE,
		AUTO_ATTENDANT_SERVICE,
		SYSTEM_WATCHDOG,
		FAX_SERVER,
		WEB_ADMIN,
		ACTIVITY,
		BACKUP;
	}	
}