package ss8_CleanCodeAndRefactoring.bai_tap;

public class TennisGame {

    public static String getScore(String player1Name, String player2Name, int m_score1, int m_score2) {
        StringBuilder score = new StringBuilder();
        int tempScore=0;
        if (m_score1==m_score2)
        {
            score = new StringBuilder(switch (m_score1) {
                case 0 -> "Love-All";
                case 1 -> "Fifteen-All";
                case 2 -> "Thirty-All";
                case 3 -> "Forty-All";
                default -> "Deuce";
            });
        }
        else if (m_score1>=4 || m_score2>=4)
        {
            int minusResult = m_score1-m_score2;
            if (minusResult==1) score = new StringBuilder("Advantage player1");
            else if (minusResult ==-1) score = new StringBuilder("Advantage player2");
            else if (minusResult>=2) score = new StringBuilder("Win for player1");
            else score = new StringBuilder("Win for player2");
        }
        else
        {
            for (int i=1; i<3; i++)
            {
                if (i==1) tempScore = m_score1;
                else { score.append("-"); tempScore = m_score2;}
                switch(tempScore)
                {
                    case 0:
                        score.append("Love");
                        break;
                    case 1:
                        score.append("Fifteen");
                        break;
                    case 2:
                        score.append("Thirty");
                        break;
                    case 3:
                        score.append("Forty");
                        break;
                }
            }
        }
        return score.toString();
    }
}