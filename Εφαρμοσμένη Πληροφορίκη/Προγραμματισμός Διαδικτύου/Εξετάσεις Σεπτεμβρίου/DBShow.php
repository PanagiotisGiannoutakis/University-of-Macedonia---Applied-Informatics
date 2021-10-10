<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>  </title>
	</head>
	
	<body>
		<script>
			var qs = (function(a) {
				if (a == "") return {};
				var b = {};
				for (var i = 0; i < a.length; ++i) {
					var p=a[i].split('=', 2);
					if (p.length == 1)
						b[p[0]] = "";
					else
						b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
				}
				return b;
			})(window.location.search.substr(1).split('&'));
		</script>
		
		<?php
			$servername = "localhost";
			$username = "root";
			$password = "";
		
			// Create connection
			$conn = new mysqli($servername, $username, $password);
			// Check connection
			if ($conn->connect_error) {
				die("Connection failed: " . $conn->connect_error);
			}
			// Create database
			$sql = "CREATE DATABASE DBSynergates";
			if ($conn->query($sql) === TRUE) {
				echo "Database created successfully";
			} else {
				echo "Error creating database: " . $conn->error;
			}
			
			$sql = "CREATE TABLE Synergates (
				id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
				fname VARCHAR(30) NOT NULL,
				email VARCHAR(30) NOT NULL
			)"; 
			
			$sql = "INSERT INTO Synergates (fname, email)
			VALUES (qs[input_name], qs[input_email)"; 
			
			$sql = "SELECT id, fname, email FROM Synergates";
			$result = $conn->query($sql);
			if ($result->num_rows > 0) {
			 // output data of each row
				while($row = $result->fetch_assoc()) {
					echo " <br> id: " . $row["id"]. " - Name: " . $row["fname"]. " " .
					$row["email"]. "<br>";
				}
			} else {
				echo "0 results";
			}
			
			$conn->close();
		?>
		
		
		
	</body>
</html>