
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaApplication2 extends JFrame implements ActionListener {

    static int[][] gr = new int[8][8], rd = new int[8][8], cn = new int[8][8];
    char pc;
    static int start1K, start2K, x, y, f = 16, s = 0, tr = 1, rr, cc, tf, prc;
    static int[] start1 = new int[8], start2 = new int[8], start1r = new int[2], start2r = new int[2], ch = new int[2];
    static JButton[][] btn = new JButton[8][8], bp = new JButton[2][4];
    static JButton[] b = new JButton[32];
    JFrame nf[][] = new JFrame[2][8];
    JPanel[] pn = new JPanel[3];
    JLabel jl = new JLabel();
    JPanel pp;
    static ImageIcon im[][] = new ImageIcon[8][8], im1[][] = new ImageIcon[2][4];
    static String st, stt, St[][] = new String[8][8], pSt[][] = new String[8][8], pl = "PLAYER ", trn[] = {"2", "1"}, M = "SAKAL PRASHONGSA POROM SRISHTIKARTAR\n";
    static String prs[][] = {{"C2a", "B2a", "D2a", "E2"}, {"C1a", "B1a", "D1a", "E1"}};
    static String imName[][] = {{"C1a", "B1a", "D1a", "E1", "F1", "D1b", "B1b", "C1b"},
        {"A1a", "A1b", "A1c", "A1d", "A1e", "A1f", "A1g", "A1h"},
        {"A2a", "A2b", "A2c", "A2d", "A2e", "A2f", "A2g", "A2h"},
        {"C2a", "B2a", "D2a", "E2", "F2", "D2b", "B2b", "C2b"}
    };

    public JavaApplication2() {
        JOptionPane.showMessageDialog(this, M);
        setTitle("CHESS GAME");//St[0]="";
        setVisible(true);
        setLocation(0, 0);
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuItem it;
        JMenu fm = new JMenu("File");
        it = new JMenuItem("new game with human");
        it.addActionListener(this);
        fm.add(it);
        it = new JMenuItem("Quit");
        it.addActionListener(this);
        fm.add(it);
        JMenu em = new JMenu("Help");
        it = new JMenuItem("About");
        it.addActionListener(this);
        em.add(it);
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        mb.add(fm);
        mb.add(em);
        BorderLayout bd = new BorderLayout();
        setLayout(bd);
        for (int i = 0; i < 3; pn[i] = new JPanel(), i++);
        pp = new JPanel();
        add(pp, bd.NORTH);
        jl.setText(pl + trn[tr]);
        pp.add(jl);
        add(pn[0], bd.CENTER);
        pn[0].setPreferredSize(new Dimension(640, 800));
        pn[0].setBackground(Color.LIGHT_GRAY);//pn[0].setLayout(null);//pn[0].setLocation(200,0);
        add(pn[1], bd.WEST);
        pn[1].setPreferredSize(new Dimension(160, 800));
        pn[1].setBackground(Color.GRAY);//pn[1].setLayout(null);//pn[1].setLocation(0,0);
        add(pn[2], bd.EAST);
        pn[2].setPreferredSize(new Dimension(160, 800));
        pn[2].setBackground(Color.GRAY);//pn[2].setLayout(null);//pn[2].setLocation(880,0);
        GridLayout gd = new GridLayout(8, 8, 5, 5);
        pn[0].setLayout(gd);
        pn[1].setLayout(new GridLayout(8, 2, 2, 2));
        pn[2].setLayout(new GridLayout(8, 2, 2, 2));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pn[0].add(btn[i][j] = new JButton(""));
                btn[i][j].setPreferredSize(new Dimension(5, 5));
            }
        }
        for (int i = 0; i < 16; i++) {
            b[i] = new JButton("");
            pn[1].add(b[i]);
            b[i].setPreferredSize(new Dimension(5, 5));
        }
        for (int i = 16; i < 32; i++) {
            b[i] = new JButton("");
            pn[2].add(b[i]);
            b[i].setPreferredSize(new Dimension(5, 5));
        }
        CL();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                btn[i][j].addActionListener(this);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                im1[i][j] = new ImageIcon(prs[i][j]);
                Image img = im1[i][j].getImage();
                Image img1 = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                im1[i][j] = new ImageIcon(img1);
            }
        }
        new JavaApplication2();
    }

    public void noCol() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rd[i][j] = gr[i][j] = cn[i][j] = 0;
                if ((i + j) % 2 == 0) {
                    btn[i][j].setBackground(Color.WHITE);
                } else {
                    btn[i][j].setBackground(Color.GRAY);
                }
            }
        }
    }

    public void CL() {
        noCol();
        tr = 1;
        prc = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cn[i][j] = 0;
                St[i][j] = "";
                im[i][j] = null;
                btn[i][j].setIcon(im[i][j]);
                if (i < 2) {
                    im[i][j] = new ImageIcon(imName[i][j]);
                    Image img = im[i][j].getImage();
                    Image img1 = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    im[i][j] = new ImageIcon(img1);
                    btn[i][j].setIcon(im[i][j]);
                    St[i][j] = imName[i][j];
                }
                if (i >= 6) {
                    im[i][j] = new ImageIcon(imName[i - 4][j]);
                    Image img = im[i][j].getImage();
                    Image img1 = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    im[i][j] = new ImageIcon(img1);
                    btn[i][j].setIcon(im[i][j]);
                    St[i][j] = imName[i - 4][j];
                }
            }
            start1[i] = start2[i] = 0;
        }
        for (int i = 0; i < 32; i++) {
            b[i].setIcon(null);
        }
        f = 16;
        s = 0;
        start1K = start2K = start1r[0] = start1r[1] = start2r[0] = start2r[1] = 0;
        jl.setText(pl + trn[tr]);
    }

    public void find(char c1, char c2) {
        for (int m = 0; m < 8; m++) {
            for (int n = 0; n < 8; n++) {
                if (St[m][n] != "") {
                    if (c1 == St[m][n].charAt(1) && c2 == St[m][n].charAt(0)) {
                        rr = m;
                        cc = n;
                        return;
                    }
                }
            }
        }
    }

    public boolean ifvalid(int i, int j, int ii, int jj, char c) {
        int bb = 1;
        String s1 = St[ii][jj], s2 = St[i][j];
        St[ii][jj] = s2;
        St[i][j] = "";

        find(c, 'F');
        int R[] = {1, -1, 0, 0}, C[] = {0, 0, 1, -1}, xx, yy, k;
        for (int m = 0; m < 4 && bb == 1; m++) {
            xx = rr + R[m];
            yy = cc + C[m];
            while (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy] == "") {
                xx += R[m];
                yy += C[m];
            }
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy].charAt(1) != c && (St[xx][yy].charAt(0) == 'C' || St[xx][yy].charAt(0) == 'E')) {
                bb = 0;
            }
        }

        if (c == '1') {
            k = 1;
        } else {
            k = -1;
        }
        if (rr + k > -1 && rr + k < 8) {
            if (cc - 1 > -1 && St[rr + k][cc - 1] != "" && St[rr + k][cc - 1].charAt(1) != c && St[rr + k][cc - 1].charAt(0) == 'A') {
                bb = 0;
            }
            if (cc + 1 < 8 && St[rr + k][cc + 1] != "" && St[rr + k][cc + 1].charAt(1) != c && St[rr + k][cc + 1].charAt(0) == 'A') {
                bb = 0;
            }
        }

        int R1[] = {-1, -2, -2, -1, 1, 2, 2, 1}, C1[] = {-2, -1, 1, 2, 2, 1, -1, -2};
        for (k = 0; k < 8 && bb == 1; k++) {
            xx = rr + R1[k];
            yy = cc + C1[k];
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy] != "") {
                if (St[xx][yy].charAt(1) != c && St[xx][yy].charAt(0) == 'B') {
                    bb = 0;
                }
            }
        }

        int R2[] = {-1, 1, 1, -1}, C2[] = {1, 1, -1, -1};
        for (int m = 0; m < 4 && bb == 1; m++) {
            xx = rr + R2[m];
            yy = cc + C2[m];
            while (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy] == "") {
                xx += R2[m];
                yy += C2[m];
            }
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy].charAt(1) != c && (St[xx][yy].charAt(0) == 'D' || St[xx][yy].charAt(0) == 'E')) {
                bb = 0;
            }
        }

        int R3[] = {-1, -1, -1, 0, 1, 1, 1, 0}, C3[] = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (k = 0; k < 8 && bb == 1; k++) {
            xx = rr + R3[k];
            yy = cc + C3[k];
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy] != "") {
                if (St[xx][yy].charAt(1) != c && St[xx][yy].charAt(0) == 'F') {
                    bb = 0;
                }
            }
        }
        St[ii][jj] = s1;
        St[i][j] = s2;
        if (bb == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void rook(int i, int j, char c) {
        int R[] = {1, -1, 0, 0}, C[] = {0, 0, 1, -1}, xx, yy;
        for (int m = 0; m < 4; m++) {
            xx = i + R[m];
            yy = j + C[m];
            while (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy] == "") {
                if (ifvalid(i, j, xx, yy, c)) {
                    gr[xx][yy] = tf = 1;
                }
                xx += R[m];
                yy += C[m];
            }
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy].charAt(1) != c) {
                if (ifvalid(i, j, xx, yy, c)) {
                    rd[xx][yy] = tf = 1;
                }
            }
        }
    }

    public void soldier(int i, int j, char c) {
        int k;
        if (c == '1') {
            k = 1;
        } else {
            k = -1;
        }
        if (j - 1 > -1 && i + k > -1 && i + k < 8) {
            if (St[i + k][j - 1] != "" && St[i + k][j - 1].charAt(1) != c && ifvalid(i, j, i + k, j - 1, c)) {
                rd[i + k][j - 1] = 1;
                tf = 1;
            }
        }
        if (j + 1 < 8 && i + k > -1 && i + k < 8) {
            if (St[i + k][j + 1] != "" && St[i + k][j + 1].charAt(1) != c && ifvalid(i, j, i + k, j + 1, c)) {
                rd[i + k][j + 1] = 1;
                tf = 1;
            }
        }
        if ((i + k > -1 && i + k < 8) && St[i + k][j] == "") {
            if (ifvalid(i, j, i + k, j, c)) {
                gr[i + k][j] = 1;
                tf = 1;
            }
        } else {
            return;
        }
        if (i + 2 * k > -1 && i + 2 * k < 8) {
            if (c == '1' && start1[St[i][j].charAt(2) - 'a'] == 0 && St[i + 2 * k][j] == "") {
                if (ifvalid(i, j, i + 2 * k, j, c)) {
                    gr[i + 2 * k][j] = 1;
                    tf = 1;
                }
            }
            if (c == '2' && start2[St[i][j].charAt(2) - 'a'] == 0 && St[i + 2 * k][j] == "") {
                if (ifvalid(i, j, i + 2 * k, j, c)) {
                    gr[i + 2 * k][j] = 1;
                    tf = 1;
                }
            }
        }
    }

    public void knight(int i, int j, char c) {
        int R[] = {-1, -2, -2, -1, 1, 2, 2, 1}, C[] = {-2, -1, 1, 2, 2, 1, -1, -2};
        for (int k = 0; k < 8; k++) {
            int xx = i + R[k];
            int yy = j + C[k];
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && ifvalid(i, j, xx, yy, c)) {
                if (St[xx][yy] == "") {
                    gr[xx][yy] = tf = 1;
                } else if (St[xx][yy].charAt(1) != c) {
                    rd[xx][yy] = tf = 1;
                }
            }
        }
    }

    public void bishop(int i, int j, char c) {
        int R[] = {-1, 1, 1, -1}, C[] = {1, 1, -1, -1}, xx, yy;
        for (int m = 0; m < 4; m++) {
            xx = i + R[m];
            yy = j + C[m];
            while (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy] == "") {
                if (ifvalid(i, j, xx, yy, c)) {
                    gr[xx][yy] = 1;
                    tf = 1;
                }
                xx += R[m];
                yy += C[m];
            }
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && St[xx][yy].charAt(1) != c) {
                if (ifvalid(i, j, xx, yy, c)) {
                    rd[xx][yy] = 1;
                    tf = 1;
                }
            }
        }
    }

    public void King(int i, int j, char c) {
        int l, m = 0, n = 0;
        String s1 = "", s2 = "";
        if (c == '1') {
            l = 0;
            if (start1K == 0 && start1r[0] == 0 && St[l][0] == "C1a") {
                m = 1;
                s1 = "C1a";
            }
            if (start1K == 0 && start1r[1] == 0 && St[l][7] == "C1b") {
                n = 1;
                s2 = "C1b";
            }
        } else {
            l = 7;
            if (start2K == 0 && start2r[0] == 0 && St[l][0] == "C2a") {
                m = 1;
                s1 = "C2a";
            }
            if (start2K == 0 && start2r[1] == 0 && St[l][7] == "C2b") {
                n = 1;
                s2 = "C2b";
            }
        }
        int R[] = {-1, -1, -1, 0, 1, 1, 1, 0}, C[] = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int k = 0; k < 8; k++) {
            int xx = i + R[k];
            int yy = j + C[k];
            if (xx > -1 && yy > -1 && xx < 8 && yy < 8 && ifvalid(i, j, xx, yy, c)) {
                if (St[xx][yy] == "") {
                    gr[xx][yy] = 1;
                    tf = 1;
                } else if (St[xx][yy].charAt(1) != c) {
                    rd[xx][yy] = 1;
                    tf = 1;
                }
            }
        }
        if (m == 1 && St[l][1] == "" && St[l][2] == "" && St[l][3] == "") {
            St[l][0] = "";
            St[l][3] = s1;
            if (ifvalid(i, j, l, 2, c)) {
                cn[l][2] = 1;
                tf = 1;
            }
            St[l][0] = s1;
            St[l][3] = "";
        }
        if (n == 1 && St[l][5] == "" && St[l][6] == "") {
            St[l][7] = "";
            St[l][5] = s2;
            if (ifvalid(i, j, l, 6, c)) {
                cn[l][6] = 1;
                tf = 1;
            }
            St[l][7] = s2;
            St[l][5] = "";
        }
    }

    public void Col() {
        for (int m = 0; m < 8; m++) {
            for (int n = 0; n < 8; n++) {
                if (gr[m][n] == 1) {
                    btn[m][n].setBackground(Color.GREEN);
                }
                if (rd[m][n] == 1) {
                    btn[m][n].setBackground(Color.RED);
                }
                if (cn[m][n] == 1) {
                    btn[m][n].setBackground(Color.CYAN);
                }
            }
        }
    }

    public boolean ifend(char c1) {
        char c;
        System.out.println(c1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (St[i][j] != "" && St[i][j].charAt(1) == c1) {
                    c = St[i][j].charAt(0);
                    tf = 0;
                    if (c == 'A') {
                        soldier(i, j, St[i][j].charAt(1));
                    } else if (c == 'C') {
                        rook(i, j, St[i][j].charAt(1));
                    } else if (c == 'B') {
                        knight(i, j, St[i][j].charAt(1));
                    } else if (c == 'D') {
                        bishop(i, j, St[i][j].charAt(1));
                    } else if (c == 'E') {
                        rook(i, j, St[i][j].charAt(1));
                        bishop(i, j, St[i][j].charAt(1));
                    } else if (c == 'F') {
                        King(i, j, St[i][j].charAt(1));
                    }
                    noCol();
                    if (tf == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void ifcheck(int i, int j, int ii, int jj, char c) {
        St[ii][jj] = St[i][j];
        St[i][j] = "";
        char c1 = (char) ('0' + '3' - c);
        if (ifvalid(ii, jj, i, j, c1) == false) {
            St[i][j] = St[ii][jj];
            St[ii][jj] = "";
            JOptionPane.showMessageDialog(this, "CHECK");
            ch[1 - tr] = 1;//noCOl();
            if (ifend(c1)) {
                JOptionPane.showMessageDialog(this, "Game Over");
                System.exit(0);
            }
        } else {
            St[i][j] = St[ii][jj];
            St[ii][jj] = "";
            noCol();
        }
        return;
    }

    public void addpr(char c, char c1) {
        int xx = '2' - c, yy = c1 - 'a';
        nf[xx][yy] = new JFrame();
        nf[xx][yy].setTitle("POWERS");
        nf[xx][yy].setVisible(true);
        nf[xx][yy].setLocation(353, 353);
        nf[xx][yy].setSize(400, 150);
        GridLayout gd1 = new GridLayout(1, 4, 5, 5);
        nf[xx][yy].setLayout(gd1);
        for (int i = 0; i < 4; i++) {
            nf[xx][yy].add(bp[xx][i] = new JButton(""));
            bp[xx][i].setIcon(im1[xx][i]);
            bp[xx][i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int m, n;
        st = ae.getActionCommand();
        if (st.equals("Quit")) {
            JOptionPane.showMessageDialog(this, "Good Luck");
            System.exit(0);
        } else if (st.equals("About")) {
            JOptionPane.showMessageDialog(this, "This programme is copyright to Shudipta Sharma\nID- 1204066\nC.S.E.,C.U.E.T.\n");
        } else if (st.equals("new game with human")) {
            CL();
        }
        for (int i = 0; i < 4 && prc == 1; i++) {
            if (bp['2' - pc][i] == ae.getSource()) {
                nf['2' - pc][St[rr][cc].charAt(2) - 'a'].dispose();
                btn[rr][cc].setIcon(im1['2' - pc][i]);
                im[rr][cc] = im1['2' - pc][i];
                St[rr][cc] = prs['2' - pc][i];
                ifcheck(rr, cc, x, y, pc);
                tr = 1 - tr;
                prc = 0;
                return;
            }
        }
        for (int i = 0; i < 32; i++) {
            if (b[i] == ae.getSource()) {
                noCol();
                return;
            }
        }
        //noCol();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (btn[i][j] == ae.getSource()) {
                    if (cn[i][j] == 1) {
                        St[i][j] = St[x][y];
                        St[x][y] = "";
                        btn[i][j].setIcon(im[x][y]);
                        im[i][j] = im[x][y];
                        im[x][y] = null;
                        if (j == 2) {
                            btn[i][3].setIcon(im[i][0]);
                            im[i][3] = im[i][0];
                            St[i][3] = St[i][0];
                            St[i][0] = "";
                            btn[i][0].setIcon(null);
                            im[i][0] = null;
                        } else {
                            btn[i][5].setIcon(im[i][7]);
                            im[i][5] = im[i][7];
                            St[i][5] = St[i][7];
                            St[i][7] = "";
                            btn[i][7].setIcon(null);
                            im[i][7] = null;
                        }
                        btn[i][4].setIcon(null);
                        ch[tr] = 0;
                        if (i == 0) {
                            if (j == 2) {
                                start1r[0] = 1;
                            } else {
                                start1r[1] = 1;
                            }
                        } else {
                            if (j == 2) {
                                start2r[0] = 1;
                            } else {
                                start2r[1] = 1;
                            }
                        }
                        if (i == 0) {
                            start1K = 1;
                        } else {
                            start2K = 1;
                        }
                        noCol();
                        ifcheck(i, j, x, y, St[i][j].charAt(1));
                        tr = 1 - tr;
                        jl.setText(pl + trn[tr]);
                    } else if (gr[i][j] == 1 || rd[i][j] == 1) {
                        St[i][j] = St[x][y];
                        if (rd[i][j] == 1) {
                            if (St[i][j].charAt(1) == '1') {
                                b[f++].setIcon(im[i][j]);
                            } else {
                                b[s++].setIcon(im[i][j]);
                            }
                        }
                        St[x][y] = "";
                        btn[i][j].setIcon(im[x][y]);
                        btn[x][y].setIcon(null);
                        im[i][j] = im[x][y];
                        im[x][y] = null;
                        ch[tr] = 0;

                        if (St[i][j].charAt(0) == 'F') {
                            if (St[i][j].charAt(1) == '1') {
                                start1K = 1;
                            } else {
                                start2K = 1;
                            }
                        }
                        if (St[i][j].charAt(0) == 'C') {
                            if (St[i][j].charAt(1) == '1') {
                                start1r[St[i][j].charAt(2) - 'a'] = 1;
                            } else {
                                start2r[St[i][j].charAt(2) - 'a'] = 1;
                            }
                        }
                        if (St[i][j].charAt(0) == 'A') {
                            int l;
                            if (St[i][j].charAt(1) == '1') {
                                start1[St[i][j].charAt(2) - 'a'] = 1;
                                l = 7;
                            } else {
                                start2[St[i][j].charAt(2) - 'a'] = 1;
                                l = 0;
                            }
                            if (i == l) {
                                prc = 1;
                                rr = i;
                                cc = j;
                                pc = St[i][j].charAt(1);
                                addpr(pc, St[i][j].charAt(2));
                                noCol();
                                return;
                            }
                        }
                        noCol();
                        ifcheck(i, j, x, y, St[i][j].charAt(1));
                        tr = 1 - tr;
                        jl.setText(pl + trn[tr]);
                    } else {
                        if (St[i][j] == "") {
                            noCol();
                            return;
                        }
                        if (St[i][j].charAt(1) == '1' && tr == 1) {
                            noCol();
                            return;
                        }
                        if (St[i][j].charAt(1) == '2' && tr == 0) {
                            noCol();
                            return;
                        }
                        noCol();
                        x = i;
                        y = j;
                        char c = St[i][j].charAt(0);
                        if (c == 'A') {
                            soldier(i, j, St[i][j].charAt(1));
                        } else if (c == 'C') {
                            rook(i, j, St[i][j].charAt(1));
                        } else if (c == 'B') {
                            knight(i, j, St[i][j].charAt(1));
                        } else if (c == 'D') {
                            bishop(i, j, St[i][j].charAt(1));
                        } else if (c == 'E') {
                            rook(i, j, St[i][j].charAt(1));
                            bishop(i, j, St[i][j].charAt(1));
                        } else if (c == 'F') {
                            King(i, j, St[i][j].charAt(1));
                        }
                        Col();
                    }
                    return;
                }
            }
        }
    }
}
