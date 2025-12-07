<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Apprenants</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
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
        .navbar h1 { font-size: 24px; }
        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .navbar a:hover { background: rgba(255,255,255,0.2); }
        .container {
            max-width: 1400px;
            margin: 30px auto;
            padding: 0 20px;
        }
        .header {
            margin-bottom: 30px;
        }
        .header h2 { color: #333; margin-bottom: 10px; }
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .stat-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .stat-card h3 {
            font-size: 36px;
            color: #667eea;
            margin-bottom: 5px;
        }
        .formation-section {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .formation-section h3 {
            color: #667eea;
            margin-bottom: 15px;
            font-size: 22px;
        }
        .apprenants-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 15px;
        }
        .apprenant-card {
            padding: 15px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            transition: all 0.3s;
        }
        .apprenant-card:hover {
            border-color: #667eea;
            transform: translateX(5px);
        }
        .apprenant-card h4 {
            color: #333;
            margin-bottom: 8px;
        }
        .apprenant-card p {
            color: #666;
            font-size: 14px;
            margin-bottom: 3px;
        }
        .badge {
            display: inline-block;
            padding: 4px 8px;
            background: #d4edda;
            color: #155724;
            border-radius: 4px;
            font-size: 12px;
            margin-top: 5px;
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
    <div>
        <a href="${pageContext.request.contextPath}/professeur/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/professeur/cours">Mes Cours</a>
        <a href="${pageContext.request.contextPath}/professeur/apprenants">Mes Apprenants</a>
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="header">
        <h2>👨‍🎓 Mes Apprenants</h2>
    </div>

    <div class="stats-grid">
        <div class="stat-card">
            <h3>${totalApprenants}</h3>
            <p>Apprenants uniques</p>
        </div>
        <div class="stat-card">
            <h3>${totalInscriptions}</h3>
            <p>Inscriptions totales</p>
        </div>
        <div class="stat-card">
            <h3>${mesFormations.size()}</h3>
            <p>Formations actives</p>
        </div>
    </div>

    <c:if test="${empty inscriptionsParFormation}">
        <div class="formation-section">
            <div class="no-data">
                Aucun apprenant inscrit à vos formations.
            </div>
        </div>
    </c:if>

    <c:forEach var="entry" items="${inscriptionsParFormation}">
        <div class="formation-section">
            <h3>📖 ${entry.key.titre}</h3>
            <p style="color: #666; margin-bottom: 15px;">
                <strong>Durée:</strong> ${entry.key.duree}h |
                <strong>Inscrits:</strong> ${entry.value.size()} / ${entry.key.capacite}
            </p>

            <c:if test="${empty entry.value}">
                <div class="no-data">
                    Aucun apprenant inscrit à cette formation.
                </div>
            </c:if>

            <div class="apprenants-grid">
                <c:forEach var="inscription" items="${entry.value}">
                    <div class="apprenant-card">
                        <h4>👤 ${inscription.apprenant.prenom} ${inscription.apprenant.nom}</h4>
                        <p>📧 ${inscription.apprenant.email}</p>
                        <p>📱 ${inscription.apprenant.telephone}</p>
                        <span class="badge">Inscrit le ${inscription.dateInscription}</span>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>