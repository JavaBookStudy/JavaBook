package com.daebal.lolinfo.champions;

/**
 * This Enum type contains subset of League of Legends champion info and stats.
 * @author daebalprime
 */

public enum ChampionsEnum {
	Ahri	("아리", 46, 30, false, Classes.Mage),
	Alistar	("알리스타", 90, 20, false, Classes.Tank),
	Ashe	("애쉬", 20, 5, false, Classes.Marksman),
	Diana	("다이애나", 32, 15, false, Classes.Mage),
	Ezreal	("이즈리얼", 142, 99, false, Classes.Marksman),
	Galio	("갈리오", 60, 40, false, Classes.Mage),
	Jinx	("징크스", 33, 25, false, Classes.Marksman),
	Leona	("레오나", 18, 2, false, Classes.Tank),
	Lulu	("룰루", 10, 9, false, Classes.Controller),
	Urgot	("우르갓", 99, 99, false, Classes.Fighter),
	Yasuo	("과학", 100, 1, true, Classes.Fighter),
	Zac		("자크", 13, 8, false, Classes.Tank);
	
	private final String 	koreanName;
	private final int 		picked;
	private final int		won;
	private final boolean	isGlobalBanned;
	private final Classes	classes;

	public enum Classes{
		Fighter,		// 전사
		Mage,			// 마법사
		Marksman,		// 원딜
		Tank,			// 탱커
		Controller, 	// 서포터
	}
	
	//computed area
	private final double	winRate;

	private ChampionsEnum(String koreanName, int picked, int won, boolean isGlobalBanned, Classes classes) {
		this.koreanName = koreanName;
		this.picked = picked;
		this.won = won;
		this.winRate = (double)won/picked;
		this.isGlobalBanned = isGlobalBanned;
		this.classes = classes;
	}	
	@Override
	public String toString() {
		return (this.koreanName + " - 승률 : " + this.winRate + " / 픽 : "+this.picked +" / 승리 : "+this.won);
	}
	public String getKoreanName() {
		return koreanName;
	}
	public int getPicked() {
		return picked;
	}
	public int getWon() {
		return won;
	}
	public boolean isGlobalBanned() {
		return isGlobalBanned;
	}
	public Classes getClasses() {
		return classes;
	}
	public double getWinRate() {
		return winRate;
	}
	
}
