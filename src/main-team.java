import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
 
public class TeamPlugin extends JavaPlugin {

    private Scoreboard scoreboard;
    private Team RedTeam;
    private Team BlueTeam;

    @Override
    public void onEnable() {
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        redTeam = scoreboard.registerNewTeam("Red");
        blueTeam = scoreboard.registerNewTeam("Blue");
    }

    @Override
    public void onDisable() {
        scoreboard.getTeams().forEach(Team::unregister);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("jointeam")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /jointeam <player> <team>");
                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }

            String teamName = args[1].toLowerCase();
            if (!teamName.equals("Red") && !teamName.equals("Blue")) {
                sender.sendMessage(ChatColor.RED + "Invalid team name, the Available teams are: Red, Blue.");
                return true;
            }

            Team team = teamName.equals("Red") ? RedTeam : BlueTeam;
            team.addEntry(player.getName());
            sender.sendMessage(ChatColor.LIME + "Player " + player.getName() + " joined the " + teamName + " team.");
            return true;
        }

        return false;
    }
}
 
