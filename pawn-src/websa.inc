#if defined websa_included
	#endinput
#endif
#define websa_included

#if defined websa_natives
	native websa_create_browser(player_id, const url[], bool:hidden, bool:touchable);
	native websa_delete_browser(player_id, browser_id);
	native websa_emit_event(player_id, browser_id, const event[]);
	native websa_subscribe(const event[], const function[]);
	native websa_hide_browser(player_id, browser_id, bool:hide);
	native websa_touch_browser(player_id, browser_id, bool:touchable);
	native websa_load_url(player_id, browser_id, const url[]);
	native websa_player_has_mod(player_id);
#endif

#if defined websa_example
	#include <a_samp>
	#include <websa>
	
	new WebSA_login[MAX_PLAYERS];
	new Error_count[MAX_PLAYERS];

	public OnGameModeInit()
	{
		websa_subscribe("websa:loginResponse", "OnPlayerLogin");
		return 1;
	}
	
	public OnPlayerConnect(playerid)
	{
		if(websa_player_has_mod(player_id))
		{
			WebSA_login[playerid] = websa_create_browser(playerid, "http://localhost:8080/login.html", false, true);
			Error_count[playerid] = 0;
		}
		return 1;
	}
	
	forward OnPlayerLogin(player_id, const args[]);
	public OnPlayerLogin(player_id, const args[])
	{
		static loginState, loginSuccess;
		loginState = args[0]; // 0: register || 1: login
		loginSuccess = args[1]; // 0: failed || 1: success
		
		if(loginSuccess)
		{
			printf("The player %s(%d) just done %s.", PName(player_id), player_id, (loginState ? "login" : "register"));
			websa_delete_browser(player_id, WebSA_login[player_id]);
			Error_count[player_id] = 0;
			// Spawn  here
		}
		else
		{
			if(Error_count[player_id]++ == 3)
			{
				static addr[32];
				GetPlayerIp(player_id, addr, sizeof(addr));
				BlockIpAddress(addr, 5 * 60 * 1000); // block ip per five minutes
				KickEx(player_id, "Error: number of attempts reached!");
			}
			else websa_emit_event(player_id, WebSA_login[player_id], "errPassword()");
		}
	}
	
	PName(playerid)
	{
		static str[24];
		GetPlayerName(playerid, str, sizeof(str));
		return str;
	}
	
	KickEx(playerid, const message[] = "")
	{
		if(message[0] != 0) SendClientMessage(playerid, 0xFF0000FF, message);
		SetTimerEx("KickPlayer", 150, 0, "d", playerid);
	}
	
	forward KickPlayer(playerid);
	public KickPlayer(playerid)
	{
		return Kick(playerid);
	}
#endif

