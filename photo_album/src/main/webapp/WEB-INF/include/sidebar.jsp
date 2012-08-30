<div class="left">

	<ul>
		<li>Administration
			<ul>
				<li><a href="viewAllUsers.htm">user list</a></li>
				<li><a href="editUser.htm?userId=${User.userId}">edit</a></li>
				<li><a href="addFriend.htm?userId=${User.userId}">friend
						list</a></li>
				<li><a href="newUser.htm">new user</a></li>
			</ul>
		</li>
		<li>Album
			<ul>
				<li><a href="allUserPhotoAlbums.htm?userId=${User.userId}">album
						list</a></li>
				<li><a href="addUserPhotoAlbum.htm?userId=${User.userId}">add
						album</a></li>
				<li><a
					href="updateUserPhotoAlbum.htm?userId=${User.userId}&albumId=${albumId}">edit
						album</a></li>
				<li><a
					href="deleteUserPhotoAlbum.htm?userId=${User.userId}&albumId=${albumId}">delete
						album</a></li>

			</ul>
		</li>

	</ul>
</div>