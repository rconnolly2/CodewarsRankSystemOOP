public class User {
    public int rank;
    public int progress;

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    public int calculateProgress(int niv_act) {
        if (!(niv_act>=8 || niv_act<-8 || niv_act==0)) {
            int dif = rankDiff(this.rank, niv_act);

            if (dif==0) {
                return 3;
            } else if (dif==-1) { // 1 rango menos: 1 punto
                return 1;
            } else if (dif<=-2) { // 2 o más rangos menos: 0 puntos
                return 0;
            } else { // Mayor rango: 10 
                //System.out.println(10*(int)Math.pow(dif, 2));
                return 10*(int)Math.pow(dif, 2);
            }

        }
        throw new IllegalArgumentException();
    }

    public int rankDiff(int nvljugador, int nvlact) { // puede que este mal
        int dif;
        if (nvljugador>0 && nvlact>0) {
            dif = Math.max(nvljugador, nvlact)-Math.min(nvljugador, nvlact);
            return (nvljugador>nvlact) ? dif*-1 : dif; // si jugador tiene mas nivel que actividad diferencia en negativo
        } else if (nvljugador>0 && nvlact<0 || nvlact>0 && nvljugador<0) {
            dif = (Math.max(nvljugador, nvlact)-(Math.min(nvljugador, nvlact)))-1;
            return (nvljugador>nvlact) ? dif*-1 : dif; // si jugador tiene mas nivel que actividad diferencia en negativo
        } else {
            dif = (Math.min(nvljugador, nvlact)-(Math.max(nvljugador, nvlact)))*-1;
            return (nvljugador>nvlact) ? dif*-1 : dif; // si jugador tiene mas nivel que actividad diferencia en negativo
        }
    }

    public void incProgress(int nvlact) {
        int puntos_gan = calculateProgress(nvlact);
        this.progress += puntos_gan;
        while (this.progress>=100) {
            this.progress -= 100;
            // subo de nivel
            if (this.rank==-1) {
                this.rank=1;
            } else if (this.rank<8) {
                this.rank+=1;
                if (this.rank==8) {
                    this.progress=0;
                }
            }
        }
    }

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.rank); // => -8
        System.out.println(user.progress);
        user.incProgress(1);
        System.out.println(user.progress);
        System.out.println(user.rank);
    }
}
