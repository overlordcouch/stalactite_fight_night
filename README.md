![splash output](/dev_documents/splash_print.jpg)  
  
    
# Stalactite Fight Night  
  
## GAMEPLAY:
In Stalactite Fight Night, you play a thrill-seeking adventurer
attempting to plumb the depths of the Caves of Neverending Despair
and Depth.  Armed with your bare hands and your trusty bathrobe, you
set off to prove your bravery in the caves!

You enter the caves and are provided with a description of your
surroundings.  You may encounter aggressive monsters that you need
to defend yourself against.  On the other hand, you may encounter 
hidden treasure chests containing a variety of helpful trash and loot.
Search caves to find less obvious loot!  Don't be discouraged if you
don't find anything.  Each time you search, you get a little better at
finding hidden loot!

If you are overcome by monsters while exploring the caves, you will wake up
in the last safe cave you were in, but the monsters may have jacked some of
your hard earned loot!

You can find armor to help protect yourself, but you are still likely
to take a beating from all of the monsters you encounter.  Your armor
has a limited durability, and will eventually fail.

## ITEMS:  Every piece of loot you find has a use!  You can use everything 
you find as a weapon, as armor, or to help heal yourself!  
  
All content in the game is randomly generated from large lists of  
possibilities, so no two games will be the same!
  
---  
  
# How to Run and Play  
  
To play, your machine needs Java 15 SDK.  Clone this repository, compile  
all .java files, and execute from the StalactiteFightNight.java class.  
  
---  
  
# The Repository  
  
The repository contains all of the classes and objects that make the game  
work.  For detailed information and API, please see the /docs folder.  

The program is highly object oriented.  Most of the functionality of the  
game is implemented as interactions between the objects that form the basis  
of the game.  
  
## Object Classes  
  
### Adventurer.java  
Describes the object that represents the player character.

### Item.java  
Acts as a super class for all loot objects that can be used or equipped  
by the player.  Has subclasses **Weapon.java, Armor.java, and Potion.java**.  
  
### Monster.java  
Describes the behavior and characteristics of the baddies that will be  
encountered by the player.
  

