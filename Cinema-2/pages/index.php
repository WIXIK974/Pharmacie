<?php
session_start();
include("../config.php");

$query = 'SELECT id, titre FROM Films';
$stmt = $pdo->prepare($query);
$stmt->execute();
$movies = $stmt->fetchAll();

include("../includes/header.php");
?>

<nav>
    <?php if (isset($_SESSION['user_id'])): ?>
        <p>Bonjour, <?php echo $_SESSION['email']; ?>!</p>
        <a href="/Cinema-2/process/logout.php">Se déconnecter</a>
    <?php else: ?>
        <a href="/Cinema-2/pages/login.php">Se connecter</a> | <a href="/Cinema-2/pages/register.php">S’inscrire</a>
    <?php endif; ?>
</nav>

<h2>Films Disponibles</h2>
<div class="movies">
    <?php foreach ($movies as $movie): ?>
    <div class="movie">
        <img src="/Cinema-2/assets/images/<?php echo strtolower(str_replace(' ', '_', $movie['titre'])); ?>.jpg" alt="<?php echo $movie['titre']; ?>">
        <h3><?php echo $movie['titre']; ?></h3>
        <a href="<?php echo isset($_SESSION['user_id']) ? '/Cinema-2/pages/noter.php?movie_id=' . $movie['id'] : '/Cinema-2/pages/login.php'; ?>">Noter ce film</a>
    </div>
    <?php endforeach; ?>
</div>

<?php include("../includes/footer.php"); ?>
