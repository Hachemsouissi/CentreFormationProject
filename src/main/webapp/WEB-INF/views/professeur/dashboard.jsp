<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Professeur</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }

        .navbar {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h1 {
            font-size: 24px;
        }

        .nav-links {
            display: flex;
            gap: 10px;
            align-items: center;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }

        .navbar a:hover {
            background: rgba(255,255,255,0.2);
        }

        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }

        .welcome {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .welcome h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
            border-top: 4px solid #667eea;
        }

        .stat-card .icon {
            font-size: 48px;
            margin-bottom: 15px;
        }

        .stat-card h3 {
            color: #333;
            font-size: 36px;
            margin-bottom: 10px;
        }

        .formations-list {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .formations-list h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .formation-item {
            padding: 20px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            margin-bottom: 15px;
            transition: all 0.3s;
        }

        .formation-item:hover {
            border-color: #667eea;
            transform: translateX(5px);
        }

        .formation-item h3 {
            color: #667eea;
            margin-bottom: 10px;
        }

        .formation-item p {
            color: #666;
            margin-bottom: 5px;
        }

        .no-data {
            text-align: center;
            padding: 40px;
            color: #999;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>🎓 Espace Professeur</h1>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/professeur/dashboard">📊 Dashboard</a>
        <a href="${pageContext.request.contextPath}/professeur/cours">📚 Mes Cours</a>
        <a href="${pageContext.request.contextPath}/professeur/apprenants">👨‍🎓 Mes Apprenants</a>
        <a href="${pageContext.request.contextPath}/logout">🚪 Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="welcome">
        <h2>Bienvenue, ${sessionScope.user.professeur.prenom} ${sessionScope.user.professeur.nom} !</h2>
        <p>Voici un aperçu de vos formations</p>
    </div>

    <div class="stats-grid">
        <div class="stat-card">
            <div class="icon">📚</div>
            <h3>${totalFormations}</h3>
            <p>Formations</p>
        </div>
    </div>

    <div class="formations-list">
        <h2>Mes Formations</h2>

        <c:if test="${empty mesFormations}">
            <div class="no-data">
                Vous n'avez aucune formation assignée pour le moment.
            </div>
        </c:if>

        <c:forEach var="formation" items="${mesFormations}">
            <div class="formation-item">
                <h3>📖 ${formation.titre}</h3>
                <p><strong>Durée :</strong> ${formation.duree} heures</p>
                <p><strong>Capacité :</strong> ${formation.capacite} places</p>
                <p><strong>Description :</strong> ${formation.description}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>