import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemProperty {

	public SystemProperty() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		Process p = null;
		/* 88 */Runtime r = Runtime.getRuntime();
		/* 89 */String OS = System.getProperty("os.name").toLowerCase();
		/*     */
		/* 91 */if (OS.indexOf("windows 9") > -1)
			/* 92 */p = r.exec("command.com /c set");
		/* 93 */else if ((OS.indexOf("nt") > -1)
				|| (OS.indexOf("windows 20") > -1) ||
				/* 94 */(OS.indexOf("windows xp") > -1))
			/* 95 */p = r.exec("cmd.exe /c set");
		/*     */else {
			/* 97 */p = r.exec("env");
			/*     */}
		/* 99 */BufferedReader br = new BufferedReader(
		/* 100 */new InputStreamReader(p.getInputStream()));
		/*     */String line;
		/* 102 */while ((line = br.readLine()) != null)
		/*     */{/*     */
			/* 103 */String[] info = line.split("=");
			/* 107 */System.out.println(line);
			/*     */}
		/* 110 */System.out.println("SystemProperty.main()");
		;

	}

}
