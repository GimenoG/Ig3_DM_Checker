package main;

public class Main {
	public enum Option {
		d, t, list;
		//MODIFIER LES OPTIONS

		public static Option getOption(String s) {
			if (s.charAt(0) != '-')
				return null;
			return Option.valueOf(s.substring(1));
		}
	}

	public static void main(String[] args) {
		StringBuilder sb  = new StringBuilder();
		sb.append(	"##############################################################\n" +
					"################# ALGORITHM PROJECT 2012-2013 ################\n" +
					"##############################################################\n" +
					"########### BY BOURGAIN PIERRE AND GIMENO GUILLAUME ##########\n" +
					"############################ IG3 #############################\n" +
					"##############################################################\n");
		StringBuilder description = new StringBuilder();
		description.append("Usage : DmChecker  -1 [options] fichierarchive \n" +
					"Usage : DmChecker  -2 [options] archivedarchives  repertoire_cible \n" +
					"[(-d|--destination) <dest>]\n" +
					"[(-o|--onetop) <onetop>]\n" +
					"[(-e|--endsWith) <endswith>]\n" +
					"[(-x|--existe) <existe>]\n" +
					"[(-i|--interdit) <interdit>]" +
					"[(-b|--beginsWith) <startswith>]\n");
		
		System.out.println(sb);
	
		
		
		if(args.length == 0 || args.length>3){
			System.out.println(description);
			System.exit(1);
		}
		
		for(int i=0; i<args.length-1; i++){
			Option o = Option.getOption(args[i]);
			if(o==null) return;
			switch(o){
			case d:
				//TODO
				break;
			case t:
				//TODO
				break;
			case list:
				//TODO
				break;
			default :
				System.out.println(description);
				System.out.println(1);
			}
		
		}
		
	}
}
