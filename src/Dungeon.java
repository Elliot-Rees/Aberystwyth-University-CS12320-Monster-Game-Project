/**
 * Dungeon composed of four interconnected rooms.
 * Rooms are created and owned internally.
 *
 * @author Elliot Rees
 * @version 1 (24/02/2026)
 */

//Todo: Add comments to explain code
public class Dungeon {
      private Room greatHall;
      private Room crypt;
      private Room westEnd;
      private Room eastEnd;
      private Room currentRoom;

      /**
       * Creates the dungeon layout and initialise all room connections.
       */
      public Dungeon() {
            buildRooms();
            connectRooms();
            this.currentRoom = this.greatHall;
      }

      /**
       * Returns the Great Hall room.
       *
       * @return great hall room
       */
      public Room getGreatHall() {
            return this.greatHall;
      }

      /**
       * Returns the Crypt room.
       *
       * @return crypt room
       */
      public Room getCrypt() {
            return this.crypt;
      }

      /**
       * Returns the West End room.
       *
       * @return west end room
       */
      public Room getWestEnd() {
            return this.westEnd;
      }

      /**
       * Returns the East End room.
       *
       * @return east end room
       */
      public Room getEastEnd() {
            return this.eastEnd;
      }

      /**
       * Returns the player's current room in the dungeon.
       *
       * @return current room
       */
      public Room getCurrentRoom() {
            return this.currentRoom;
      }

      /**
       * Sets the current room if not null.
       *
       * @param currentRoom room to set as current
       */
      public void setCurrentRoom(Room currentRoom) {
            if (currentRoom != null) {
                  this.currentRoom = currentRoom;
            }
      }

      /**
       * Returns all rooms in this dungeon.
       *
       * @return array of four rooms
       */
      public Room[] getAllRooms() {
            return new Room[]{this.greatHall, this.crypt, this.westEnd, this.eastEnd};
      }

      /**
       * Builds room objects for dungeon.
       */
      private void buildRooms() {
            this.greatHall = new Room("Great Hall");
            this.crypt = new Room("Crypt");
            this.westEnd = new Room("West End");
            this.eastEnd = new Room("East End");
      }

      /**
       * Connects rooms according to the worksheet diagram. (Temp? Probably will be replaced later)
       */
      private void connectRooms() {
            this.greatHall.setEast(this.crypt);
            this.greatHall.setSouth(this.westEnd);
            this.crypt.setWest(this.greatHall);
            this.crypt.setSouth(this.eastEnd);
            this.westEnd.setNorth(this.greatHall);
            this.westEnd.setEast(this.eastEnd);
            this.eastEnd.setNorth(this.crypt);
            this.eastEnd.setWest(this.westEnd);
      }
}
