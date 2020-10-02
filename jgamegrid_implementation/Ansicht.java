package jgamegrid_implementation;

abstract class Ansicht {

  Spielfeld spielfeld;

  public Ansicht(Spielfeld spielfeld) {
    this.spielfeld = spielfeld;
  }

  public abstract void zeige();

}
