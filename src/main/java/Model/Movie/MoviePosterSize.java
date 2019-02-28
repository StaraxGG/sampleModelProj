package Model.Movie;

public enum MoviePosterSize {

    W342 {
        @Override
        public String toString() {
            return "w342";
        }
    },
    W500 {
        @Override
        public String toString() {
            return "w500";
        }
    },
    W700 {
        @Override
        public String toString() {
            return "w700";
        }
    },
    ORIGINAL {
        @Override
        public String toString() {
            return "original";
        }
    }

}
