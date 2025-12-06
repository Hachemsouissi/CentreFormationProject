<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${user == null ? 'Ajouter' : 'Modifier'} un Utilisateur</title>
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
        }
        .navbar h1 { font-size: 24px; }
        .container {
            max-width: 600px;
            margin: 30px auto;
            padding: 0 20px;
        }
        .form-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .form-header {
            margin-bottom: 30px;
        }
        .form-header h2 {
            color: #333;
            margin-bottom: 10px;
        }
        .form-header a {
            color: #667eea;
            text-decoration: none;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            color: #333;
            font-weight: 500;
            margin-bottom: 8px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        .form-group input:focus, .form-group select:focus {
            outline: none;
            border-color: #667eea;
        }
        .error-message {
            background: #fee;
            color: #c33;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }
        .btn-submit, .btn-cancel {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }
        .btn-submit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .btn-cancel {
            background: #6c757d;
            color: white;
        }
        .btn-submit:hover { opacity: 0.9; }
        .btn-cancel:hover { background: #5a6268; }
        .help-text {
            font-size: 12px;
            color: #999;
            margin-top: 5px;
        }
        #apprenantGroup, #professeurGroup {
            display: none;
        }
    </style>
    <script>
        function togglePersonnelFields() {
            const role = document.getElementById('role').value;
            const apprenantGroup = document.getElementById('apprenantGroup');
            const professeurGroup = document.getElementById('professeurGroup');

            apprenantGroup.style.display = 'none';
            professeurGroup.style.display = 'none';

            if (role === 'apprenant') {
                apprenantGroup.style.display = 'block';
            } else if (role === 'professeur') {
                professeurGroup.style.display = 'block';
            }
        }

        window.onload = function() {
            togglePersonnelFields();
        }
    </script>
</head>
<body>
<nav class="navbar">
    <h1>🎓 Gestion Formation</h1>
</nav>

<div class="container">
    <div class="form-card">
        <div class="form-header">
            <h2>${user == null ? '➕ Ajouter' : '✏️ Modifier'} un Utilisateur</h2>
            <a href="${pageContext.request.contextPath}/admin/users">← Retour à la liste</a>
        </div>

        <c:if test="${error != null}">
            <div class="error-message">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/users" method="post">
            <input type="hidden" name="action" value="${user == null ? 'add' : 'edit'}">
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="${user.id}">
            </c:if>

            <div class="form-group">
                <label for="username">Nom d'utilisateur *</label>
                <input type="text" id="username" name="username"
                       value="${user != null ? user.username : ''}" required>
            </div>

            <div class="form-group">
                <label for="password">${user == null ? 'Mot de passe' : 'Nouveau mot de passe'} *</label>
                <input type="password" id="password"
                       name="${user == null ? 'password' : 'newPassword'}"
                ${user == null ? 'required' : ''}>
                <c:if test="${user != null}">
                    <span class="help-text">Laissez vide pour ne pas changer le mot de passe</span>
                </c:if>
            </div>

            <div class="form-group">
                <label for="role">Rôle *</label>
                <select id="role" name="role" onchange="togglePersonnelFields()" required>
                    <option value="">-- Sélectionner un rôle --</option>
                    <option value="admin" ${user != null && user.role == 'admin' ? 'selected' : ''}>
                        Administrateur
                    </option>
                    <option value="professeur" ${user != null && user.role == 'professeur' ? 'selected' : ''}>
                        Professeur
                    </option>
                    <option value="apprenant" ${user != null && user.role == 'apprenant' ? 'selected' : ''}>
                        Apprenant
                    </option>
                </select>
            </div>

            <div class="form-group" id="apprenantGroup">
                <label for="apprenantId">Lier à un apprenant</label>
                <select id="apprenantId" name="apprenantId">
                    <option value="">-- Sélectionner un apprenant --</option>
                    <c:forEach var="apprenant" items="${apprenants}">
                        <option value="${apprenant.id}"
                            ${user != null && user.apprenant != null && user.apprenant.id == apprenant.id ? 'selected' : ''}>
                                ${apprenant.nom} ${apprenant.prenom} (${apprenant.email})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group" id="professeurGroup">
                <label for="professeurId">Lier à un professeur</label>
                <select id="professeurId" name="professeurId">
                    <option value="">-- Sélectionner un professeur --</option>
                    <c:forEach var="professeur" items="${professeurs}">
                        <option value="${professeur.id}"
                            ${user != null && user.professeur != null && user.professeur.id == professeur.id ? 'selected' : ''}>
                                ${professeur.nom} ${professeur.prenom} (${professeur.email})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn-submit">
                    ${user == null ? '➕ Ajouter' : '💾 Enregistrer'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/users" class="btn-cancel">
                    ❌ Annuler
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>