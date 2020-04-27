(function() {

// zones de jeu
var brique = document.getElementById('brique');
var balle = document.getElementById('balle');
var jeu = document.getElementById('bloc_jeu');
var zone_mur = document.getElementById('zone_mur');
var zone_murHteur = zone_mur.offsetHeight;
var zone_balle = document.getElementById('zone_balle');
var zone_balleHteur = zone_balle.offsetTop;

//attention, les balles ont un offset en absolu, alors que leur position de style est en relatif
//brique
var BriqueDeplRelH = 0;
var BriqueDeplRelGMax = - (brique.offsetLeft - zone_balle.offsetLeft);	//dep. neg
var BriqueDeplRelDMax = -BriqueDeplRelGMax;							//dep. pos
var BriqueLargeur = brique.offsetWidth;
// balle
var BalleDeplRelH = 0;
var BalleDeplRelGMax = - (balle.offsetLeft - zone_balle.offsetLeft); 	//dep. neg
var BalleDeplRelDMax = -BalleDeplRelGMax;								//dep. pos
var BalleDeplRelGMaxStart = - (balle.offsetLeft - zone_balle.offsetLeft - Math.floor(BriqueLargeur/2) + Math.floor(balle.offsetWidth/2));
var BalleDeplRelDMaxStart = -BalleDeplRelGMaxStart;						
var BalleDeplRelV = 0;
var BalleDeplRelHMax = - (balle.offsetTop - zone_balle.offsetTop); 	
var BalleDeplRelBMax = 0;
var BalleDeplRelDebMur = 	BalleDeplRelHMax + zone_mur.offsetHeight;							

// mur
var murs = document.getElementsByClassName('mur');
var nbMurs = murs.length;
// variables dynamiques de déplacement
var ricochetB = 0;
var ricochetB2 = 0;
var ricochetMur = false;
var ricochetBas = true;
var start = true;
var end = false;
var sensVert = -10;
var sensHori = 0;
// divers
var intervalId = null;


document.addEventListener('keydown', function (e) {
	if (e.keyCode == 37) { // gauche
		deplBrique(-10);
		//ricochetB = -5; // désactivé car dur de gagner !
	}
	if (e.keyCode == 39) { // droite
		deplBrique(10);
		//ricochetB = 5; // désactivé car dur de gagner !
	}
});

function deplBrique (i) {
	BriqueDeplRelH +=i;
	if (BriqueDeplRelH < BriqueDeplRelGMax) {BriqueDeplRelH = BriqueDeplRelGMax;}
	if (BriqueDeplRelH > BriqueDeplRelDMax) {BriqueDeplRelH = BriqueDeplRelDMax;}
	brique.style.left = BriqueDeplRelH.toString() + "px";

	if (start) {
		BalleDeplRelH +=i;
		if (BalleDeplRelH < BalleDeplRelGMaxStart) {BalleDeplRelH = BalleDeplRelGMaxStart;}
		if (BalleDeplRelH > BalleDeplRelDMaxStart) {BalleDeplRelH = BalleDeplRelDMaxStart;}
		balle.style.left = BalleDeplRelH.toString() + "px";
	}
}

function ctrlMur() {		
	var touchee = false;
	var bordG = 0; var bordD = 0; var bordH = 0; var bordB = 0;
	var balleG = balle.offsetLeft;
	var balleD = balle.offsetLeft + balle.offsetWidth;
	var balleH = balle.offsetTop;
	var balleB = balle.offsetTop + balle.offsetHeight;
	for (var i = 0; i < murs.length; i++) {
		bordH = murs[i].offsetTop;
		bordB = murs[i].offsetTop + murs[i].offsetHeight;
		bordG = murs[i].offsetLeft;
		bordD = murs[i].offsetLeft + murs[i].offsetWidth;
		if (    ( ( balleH >= bordH ) && ( balleH <= bordB ) ) 				// bord haut
			 && (    ( ( balleG >= bordG ) && ( balleG <= bordD ) )     	// bord gauche
			      || ( ( balleD >= bordG ) && ( balleD <= bordD ) ) )  ) 	// bord droit
			      {	// touché !
			if (murs[i].innerHTML == 'N') { // Ctrl si pas déjà touché, TODO : ajouter une propriété plutôt 
				murs[i].style.backgroundColor = 'green';
				murs[i].style.color = 'green';
				murs[i].style.border = 'solid green';
				murs[i].innerHTML = 'O';
				touchee = true;
				nbMurs--;
			}
			//alert("touché");
		}
	}

	return touchee;
}

function ctrlBrique() {		
	var rebond = 0;
	if ( ( (balle.offsetLeft + balle.offsetWidth) < brique.offsetLeft) 
		|| ( balle.offsetLeft > (brique.offsetLeft + brique.offsetWidth) ) ) {
		alert("Vous avez perdu");
	} else {
		// définition des zones de rebond sur la brique
		var limZone1D = brique.offsetLeft + Math.floor(BriqueLargeur / 3);
		var limZone2D = limZone1D + Math.floor(BriqueLargeur / 3);
		if (balle.offsetLeft <= limZone1D) {
			rebond = -5;
		}
		if (balle.offsetLeft >= limZone2D) {
			rebond = 5;
		}
	}
	ricochetBas = true;
	return rebond;
}

document.addEventListener('keyup', function (e) {
	if (e.keyCode == 37) { // gauche
		ricochetB = 0;
	}
	if (e.keyCode == 39) { // droite
		ricochetB = 0;
	}
	if (e.keyCode == 32 && start == true) { // espace
		start = false;		  
		sensHori += ricochetB;
		ricochetBas = false;
		//do {
			intervalId = setInterval(function() {	// impossible de le mettre dans une fonction à part ... (ca ne boucle pas)

			BalleDeplRelV +=sensVert;
			if (BalleDeplRelV < BalleDeplRelHMax) {
				BalleDeplRelV = BalleDeplRelHMax;
				sensVert=(-1)*sensVert;
				BalleDeplRelV +=sensVert;
			}
			if (BalleDeplRelV > BalleDeplRelBMax) {
				ricochetB2 = ctrlBrique();
				BalleDeplRelV = BalleDeplRelBMax;
				sensVert=(-1)*sensVert;
				BalleDeplRelV +=sensVert;
			}
			if (BalleDeplRelV < BalleDeplRelDebMur) {
				ricochetMur = ctrlMur();
				if (ricochetMur) {
					sensVert=10;
					BalleDeplRelV +=sensVert;
				}
				if (nbMurs == 0) {alert("Vous avez gagné !!");}
			}
			balle.style.top = BalleDeplRelV.toString() + "px";	

			if (ricochetBas == true) {
				sensHori += ricochetB;
				sensHori += ricochetB2;
				ricochetBas = false;
			}
			BalleDeplRelH +=sensHori;
			if (BalleDeplRelH < BalleDeplRelGMax) {
				BalleDeplRelH = BalleDeplRelGMax;
				sensHori=(-1)*sensHori;
				BalleDeplRelH +=sensHori;
			}
			if (BalleDeplRelH > BalleDeplRelDMax) {
				BalleDeplRelH = BalleDeplRelDMax;
				sensHori=(-1)*sensHori;
				BalleDeplRelH +=sensHori;
			}
			balle.style.left = BalleDeplRelH.toString() + "px";

			}, 50);
		//} while (end == false) // boucle à revoir (booléen surement en pb ou boucle do/while)

	}

});

})();



