<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Gestion Formation</title>
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

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
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

        .welcome p {
            color: #666;
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
            transition: transform 0.3s;
        }

        .stat-card:hover {
            transform: translateY(-5px);
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

        .stat-card p {
            color: #666;
            font-size: 16px;
        }

        .stat-card.apprenants {
            border-top: 4px solid #667eea;
        }

        .stat-card.formations {
            border-top: 4px solid #28a745;
        }

        .stat-card.inscriptions {
            border-top: 4px solid #ffc107;
        }

        .stat-card.professeurs {
            border-top: 4px solid #dc3545;
        }

        .quick-actions {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .quick-actions h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .actions-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
        }

        .action-btn {
            display: block;
            padding: 15px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            text-align: center;
            transition: transform 0.3s;
        }

        .action-btn:hover {
            transform: translateY(-3px);
        }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>🎓 Gestion Formation</h1>
    <div>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/apprenants">Apprenants</a>
        <a href="${pageContext.request.contextPath}/admin/formations">Formations</a>
        <a href="${pageContext.request.contextPath}/admin/inscriptions">Inscriptions</a>
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="welcome">
        <h2>Bienvenue, ${sessionScope.user.username} !</h2>
        <p>Voici un aperçu de votre système de gestion de formation</p>
    </div>

    <div class="stats-grid">
        <div class="stat-card apprenants">
            <div class="icon">👨‍🎓</div>
            <h3>${totalApprenants}</h3>
            <p>Apprenants</p>
        </div>

        <div class="stat-card formations">
            <div class="icon">📚</div>
            <h3>${totalFormations}</h3>
            <p>Formations</p>
        </div>

        <div class="stat-card inscriptions">
            <div class="icon">📝</div>
            <h3>${totalInscriptions}</h3>
            <p>Inscriptions</p>
        </div>

        <div class="stat-card professeurs">
            <div class="icon">👨‍🏫</div>
            <h3>${totalProfesseurs}</h3>
            <p>Professeurs</p>
        </div>
    </div>

    <div class="quick-actions">
        <h2>Actions rapides</h2>
        <div class="actions-grid">
            <a href="${pageContext.request.contextPath}/admin/apprenants?action=add" class="action-btn">
                ➕ Ajouter un apprenant
            </a>
            <a href="${pageContext.request.contextPath}/admin/formations?action=add" class="action-btn">
                ➕ Ajouter une formation
            </a>
            <a href="${pageContext.request.contextPath}/admin/inscriptions?action=add" class="action-btn">
                ➕ Nouvelle inscription
            </a>
            <a href="${pageContext.request.contextPath}/admin/apprenants" class="action-btn">
                👨‍🎓 Gérer les apprenants
            </a>
        </div>
    </div>
</div>
</body>
</html>