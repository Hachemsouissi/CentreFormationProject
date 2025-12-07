<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Apprenant</title>
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
            border-top: 4px solid #28a745;
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

        .inscriptions-list {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .inscriptions-list h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .inscription-item {
            padding: 20px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            margin-bottom: 15px;
            transition: all 0.3s;
        }

        .inscription-item:hover {
            border-color: #28a745;
            transform: translateX(5px);
        }

        .inscription-item h3 {
            color: #28a745;
            margin-bottom: 10px;
        }

        .inscription-item p {
            color: #666;
            margin-bottom: 5px;
        }

        .badge {
            display: inline-block;
            padding: 5px 10px;
            background: #d4edda;
            color: #155724;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 600;
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
    <h1>🎓 Espace Apprenant</h1>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/apprenant/dashboard"> Dashboard</a>
        <a href="${pageContext.request.contextPath}/apprenant/cours"> Mes Cours</a>
        <a href="${pageContext.request.contextPath}/logout"> Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="welcome">
        <h2>Bienvenue, ${sessionScope.user.apprenant.prenom} ${sessionScope.user.apprenant.nom} !</h2>
        <p>Voici un aperçu de vos inscriptions</p>
    </div>

    <div class="stats-grid">
        <div class="stat-card">
            <div class="icon">📝</div>
            <h3>${totalInscriptions}</h3>
            <p>Inscriptions</p>
        </div>
    </div>

    <div class="inscriptions-list">
        <h2>Mes Inscriptions</h2>

        <c:if test="${empty mesInscriptions}">
            <div class="no-data">
                Vous n'êtes inscrit à aucune formation pour le moment.
            </div>
        </c:if>

        <c:forEach var="inscription" items="${mesInscriptions}">
            <div class="inscription-item">
                <h3>📖 ${inscription.formation.titre}</h3>
                <p><strong>Durée :</strong> ${inscription.formation.duree} heures</p>
                <p><strong>Professeur :</strong>
                        ${inscription.formation.professeur.prenom} ${inscription.formation.professeur.nom}
                </p>
                <p><strong>Date d'inscription :</strong> ${inscription.dateInscription}</p>
                <p><span class="badge">✓ Inscrit</span></p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>