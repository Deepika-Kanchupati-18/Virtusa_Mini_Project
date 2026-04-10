# CityCab Fare Calculator - FareCalc Module

def calculate_fare(distance, vehicle_type, hour):
    #Define pricing
    pricing_table = {
        "Bike":20,
        "Auto":25,
        "Economy": 30,
        "Premium": 40,
        "SUV": 50
    }

    # Validate vehicle type
    if vehicle_type not in pricing_table:
        return None, "Service Not Available"

    #Calculate base fare
    rate = pricing_table[vehicle_type]
    base_fare = distance * rate

    #Apply surge if peak hour
    surge_applied = False
    if 17 <= hour <= 20:
        base_fare *= 1.5
        surge_applied = True

    return base_fare, surge_applied


#----------- MAIN-----------

def main():
    print("city cab ride estimation")
    try:
        #Take user inputs
        distance = float(input("Enter distance (in km): "))
        vehicle_type = input("Enter vehicle type (Economy/Premium/SUV): ").title()
        hour = int(input("Enter hour of travel (0-23): "))

        #Function call
        fare, status = calculate_fare(distance, vehicle_type, hour)

        #Handle invalid vehicle
        if fare is None:
            print("\n" + status)
            return

        # Print receipt
        print("\n Ride Receipt ")
        print(f"Distance       : {distance} km")
        print(f"Vehicle Type   : {vehicle_type}")

        if status:
            print("Surge Pricing  : Applied (1.5x)")
        else:
            print("Surge Pricing  : Not Applied")

        print(f"Final Fare     : ₹{round(fare, 2)}")

    except ValueError:
        print("\nInvalid input! Please enter correct numeric values.")


# Run the program
main()
