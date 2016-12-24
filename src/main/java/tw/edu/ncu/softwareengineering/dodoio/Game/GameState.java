package tw.edu.ncu.softwareengineering.dodoio.Game;

public enum GameState {
	CONTINUE,					//nothing happens
	PLAYERDEAD,					//player is dead(all game mode will have this state)
	PLAYERWIN,					//for DeathMatch
	PLAYERTEAMWIN, ENEMYTEAMWIN	//for KingKill
}
