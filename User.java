class User {
    public int rank;
    public int progress;

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    public int calculateProgress(int niv_act) {
        if (!(niv_act > 8 || niv_act < -8 || niv_act == 0)) {
            int dif = rankDiff(this.rank, niv_act);

            if (dif == 0) {
                return 3;
            } else if (dif == -1) { // 1 rango menos: 1 punto
                return 1;
            } else if (dif <= -2) { // 2 o más rangos menos: 0 puntos
                return 0;
            } else { // Mayor rango: 10 
                return 10 * dif * dif;
            }

        }
        throw new IllegalArgumentException();
    }

    public int rankDiff(int nvljugador, int nvlact) { // puede que este mal
        int dif;
        if (nvljugador > 0 && nvlact > 0) {
            dif = Math.max(nvljugador, nvlact) - Math.min(nvljugador, nvlact);
            return (nvljugador > nvlact) ? dif * -1 : dif; // si jugador tiene mas nivel que actividad diferencia en negativo
        } else if (nvljugador > 0 && nvlact < 0 || nvlact > 0 && nvljugador < 0) {
            dif = (Math.max(nvljugador, nvlact) - (Math.min(nvljugador, nvlact))) - 1;
            return (nvljugador > nvlact) ? dif * -1 : dif; // si jugador tiene mas nivel que actividad diferencia en negativo
        } else {
            dif = (Math.min(nvljugador, nvlact) - (Math.max(nvljugador, nvlact))) * -1;
            return (nvljugador > nvlact) ? dif * -1 : dif; // si jugador tiene mas nivel que actividad diferencia en negativo
        }
    }

    public void incProgress(int nvlact) {
        int puntos_gan = calculateProgress(nvlact);
        if (this.rank == 8) { // si el nivel es máximo reseteo progreso
            this.progress = 0; 
        } else {
            this.progress += puntos_gan;
        }
        while (this.progress >= 100) { // itero hasta 
            this.progress -= 100;
            // subo de nivel
            if (this.rank == -1) { // salto nivel 0
                this.rank = 1;
            } else if (this.rank < 8) { // si es menor a 8 subo de nivel
                this.rank += 1;
                if (this.rank == 8) {
                    this.progress = 0; 
                }
            }
        }
    }

}
