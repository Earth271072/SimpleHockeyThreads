import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.*;

public class Line {

		private Document doc;
		private String[] F1 = new String[3];
		private String[] F2 = new String[3];
		private String[] F3 = new String[3];
		private String[] F4 = new String[3];
		private String[] D1 = new String[2];
		private String[] D2 = new String[2];
		private String[] D3 = new String[2];
		private String[] PPF1 = new String[3];
		private String[] PPF2 = new String[3];
		private String[] PPD1 = new String[2];
		private String[] PPD2 = new String[2];
		private String[] G = new String[2];
		private String[] IR = new String[12];

		public Line (String statsUrl) {
			try {
				doc = Jsoup.connect(statsUrl).get();
				F1[0] = doc.select("td#LW1 a").text();
				F2[0] = doc.select("td#LW2 a").text();
				F3[0] = doc.select("td#LW3 a").text();
				F4[0] = doc.select("td#LW4 a").text();
				F1[1] = doc.select("td#C1 a").text();
				F2[1] = doc.select("td#C2 a").text();
				F3[1] = doc.select("td#C3 a").text();
				F4[1] = doc.select("td#C4 a").text();
				F1[2] = doc.select("td#RW1 a").text();
				F2[2] = doc.select("td#RW2 a").text();
				F3[2] = doc.select("td#RW3 a").text();
				F4[2] = doc.select("td#RW4 a").text();
				D1[0] = doc.select("td#LD1 a").text();
				D2[0] = doc.select("td#LD2 a").text();
				D3[0] = doc.select("td#LD3 a").text();
				D1[1] = doc.select("td#RD1 a").text();
				D2[1] = doc.select("td#RD2 a").text();
				D3[1] = doc.select("td#RD3 a").text();
				PPF1[0] = doc.select("td#PPLW1 a").text();
				PPF2[0] = doc.select("td#PPLW2 a").text();
				PPF1[1] = doc.select("td#PPC1 a").text();
				PPF2[1] = doc.select("td#PPC2 a").text();
				PPF1[2] = doc.select("td#PPRW1 a").text();
				PPF2[2] = doc.select("td#PPRW2 a").text();
				PPD1[0] = doc.select("td#PPLD1 a").text();
				PPD2[0] = doc.select("td#PPLD2 a").text();
				PPD1[1] = doc.select("td#PPRD1 a").text();
				PPD2[1] = doc.select("td#PPRD2 a").text();
				G[0] = doc.select("td#G1 a").text();
				G[1] = doc.select("td#G2 a").text();
				IR[0] = doc.select("td#IR1 a").text();
				IR[1] = doc.select("td#IR2 a").text();
				IR[2] = doc.select("td#IR3 a").text();
				IR[3] = doc.select("td#IR4 a").text();
				IR[4] = doc.select("td#IR5 a").text();
				IR[5] = doc.select("td#IR6 a").text();
				IR[6] = doc.select("td#IR7 a").text();
				IR[7] = doc.select("td#IR8 a").text();
				IR[8] = doc.select("td#IR9 a").text();
				IR[9] = doc.select("td#IR10 a").text();
				IR[10] = doc.select("td#IR11 a").text();
				IR[11] = doc.select("td#IR12 a").text();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		public String[][] getForwards() {
			String[][] F = new String[4][3];
			F[0] = getForward1();
			F[1] = getForward2();
			F[2] = getForward3();
			F[3] = getForward4();
			return F;
		}

		public String[][] getDefense() {
			String[][] D = new String[3][2];
			D[0] = getDefense1();
			D[1] = getDefense2();
			D[2] = getDefense3();
			return D;
		}

		public String[] getForward1() {
			return F1;
		}

		public String[] getForward2() {
			return F2;
		}

		public String[] getForward3() {
			return F3;
		}

		public String[] getForward4() {
			return F4;
		}

		public String[] getDefense1(){
			return D1;
		}

		public String[] getDefense2(){
			return D2;
		}

		public String[] getDefense3(){
			return D3;
		}

		public String[] getPPF1() {
			return PPF1;
		}

		public String[] getPPF2() {
			return PPF2;
		}

		public String[] getPPD1() {
			return PPD1;
		}

		public String[] getPPD2() {
			return PPD2;
		}

		public String[] getGoalies() {
			return G;
		}

		public String[] getIR() {
			return IR;
		}


}

