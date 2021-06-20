package com.daebal.lolinfo.main;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.daebal.lolinfo.champions.ChampionsEnum;

public class MainState {
	private static Map<ChampionsEnum.Classes, List<ChampionsEnum>> champsByClass;
//	
//	private static List<ChampionsEnum> BanByTeam1 = new ArrayList<>();
//	private static List<ChampionsEnum> BanByTeam2 = new ArrayList<>();
//	private static List<ChampionsEnum> PickByTeam1 = new ArrayList<>();
//	private static List<ChampionsEnum> PickByTeam2 = new ArrayList<>();
//	
//	private static String[] team = {"Blue", "Red"};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		champsByClass = new EnumMap<>(ChampionsEnum.Classes.class);
		for(ChampionsEnum.Classes cls : ChampionsEnum.Classes.values()) {
			champsByClass.put(cls, new ArrayList<>());
		}
		
		for(ChampionsEnum champ : ChampionsEnum.values()) {
			champsByClass.get(champ.getClasses()).add(champ);
		}
		
		for(ChampionsEnum.Classes cls : ChampionsEnum.Classes.values()) {
			System.out.println("---------------------------");
			System.out.println(cls.name() + "군에 속한 챔피언들");
			List<ChampionsEnum> champs = champsByClass.get(cls);
			for(ChampionsEnum champ : champs) {
				System.out.print(champ.toString());
				if(champ.isGlobalBanned()) {
					System.out.println("** 글로벌 밴 적용 ** ");
				}else {
					System.out.println();
				}
			}
		}
	}
//		for(int i = 0; i < 6; i++) {
//			System.out.println(team[i%2]+"금지 : ");
//			String ban = sc.next();
//			
//			
//			print();
//			
//		}
//		
//		
//		
//		
//		for(int i = 0; i < 10; i++) {
//			System.out.println(((i+1)/2)%2);
//		}
//	}
//	
//	private StringBuilder print() {
//		StringBuilder sb = new StringBuilder("------------------------------------------------------\n");
//		sb.append("금지된 챔피언 : ");
//		for(ChampionsEnum c : BanByTeam1) {
//			sb.append(c).append("\t");
//		}
//		sb.append("///");
//		for(ChampionsEnum c : BanByTeam2) {
//			sb.append(c).append("\t");
//		}
//		sb.append("\n======================================\n");
//		sb.append("레드팀 : ");
//		for(ChampionsEnum c : PickByTeam1) {
//			sb.append(c.getKoreanName()).append("\t");
//		}
//		sb.append("\n======================================\n");
//		sb.append("블루팀 : ");
//		for(ChampionsEnum c : PickByTeam2) {
//			sb.append(c.getKoreanName()).append("\t");
//		}
//		sb.append("\n======================================\n");
//		return sb;
//	}
//	
	
	
	
}
