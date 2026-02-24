/**
 * @author Elliot Rees
 * @version 1 (24/02/2026)
 */

//Todo: Add comments to explain code
public class Room {
      private String name; // Ignore Final Warning
      private Room north;
      private Room south;
      private Room east;
      private Room west;

      /**
       * Creates a room with display name.
       *
       * @param name room name
       */
      public Room(String name) {
            this.name = name;
      }

      /**
       * Returns the room name.
       *
       * @return room name
       */
      public String getName() {
            return this.name;
      }

  /**
       * Sets the room connected to the north.
       *
       * @param north room to the north
       */
      public void setNorth(Room north) {
            this.north = north;
      }

  /**
       * Sets the room connected to the south.
       *
       * @param south room to the south
       */
      public void setSouth(Room south) {
            this.south = south;
      }

  /**
       * Sets the room connected to the east.
       *
       * @param east room to the east
       */
      public void setEast(Room east) {
            this.east = east;
      }

  /**
       * Sets the room connected to the west.
       *
       * @param west room to the west
       */
      public void setWest(Room west) {
            this.west = west;
      }

  /**
       * Returns a summary of room and all directional links.
       *
       * @return room summary
       */
      @Override
      public String toString() {
            return "Room '" + this.name + "' -> " + "North: " + nameOrNone(this.north) + ", "+ "South: " + nameOrNone(this.south) + ", "+ "East: " + nameOrNone(this.east) + ", "+ "West: " + nameOrNone(this.west);
      }

      /**
       * Returns the target room name or "None" for a missing connection.
       *
       * @param room neighboring room reference
       * @return room name or {@code "None"}
       */
      private String nameOrNone(Room room) {
            return room == null ? "None" : room.getName();
      }
}
