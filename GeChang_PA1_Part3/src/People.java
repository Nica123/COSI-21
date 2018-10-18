public class People{
  // the name of the player
  public String name;
  //the SpeakingClarity of the player
  public double SpeakingClarity;
  //the ListeningAbility of the player
  public double ListeningAbility;
  
  /** this is the constructor of People class
  @param name, the name of the player
  @param SpeakingClarity, the SpeakingClarity of the player
  @param ListeningAblity, the ListeningAbility of the player
  */
  public People(String name, double SpeakingClarity, double ListeningAbility){
    this.name = name;
    this.SpeakingClarity = SpeakingClarity;
    this.ListeningAbility = ListeningAbility;
  }
  
  /** this is the getter of name
  @return the name of the person
  */
  public String getName(){
    return this.name;
  }
  /** this is the getter of Speaking Clarity
  @return the Speaking Clarity of the person
  */
  public double getSpeakingClarity(){
    return this.SpeakingClarity;
  }
  /** this is the getter of Listening Ability
  @return the listening ability of the person
  */
  public double getListeningAbility(){
    return this.ListeningAbility;
  }

  /** this is the toString method of the People object
  @return the String want to print or for other use
  */
  public String toString(){
    return this.name +" SpeakingClarity: "+ this.SpeakingClarity + " ListeningAbility: " + this.ListeningAbility;
  }
}
