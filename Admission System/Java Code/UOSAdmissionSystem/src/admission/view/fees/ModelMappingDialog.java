package admission.view.fees;

import admission.controller.DatabaseManager;
import admission.enums.MessageEnum;
import admission.enums.OrientedEnum;
import admission.model.Discipline;
import admission.model.Faculty;
import admission.model.Part;
import admission.model.Program;
import admission.model.ProgramType;
import admission.model.fee.FeeModel;
import admission.model.fee.ModelMapping;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import admission.utils.MessageBox;
import admission.utils.RoundedBorder;

/**
 *
 * @author Adeel, Yougeshwar Khatri
 */
public class ModelMappingDialog extends javax.swing.JDialog {

    public ModelMappingDialog(FeeModelInternalFrame parent) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();

        admission.utils.Utility.hideOnEscape(this);
        this.titlePanel.setBorder(RoundedBorder.createGradientBorder());
        
        admission.utils.Utility.comboBoxScroll(facultyComboBox);
        admission.utils.Utility.comboBoxScroll(programComboBox);
        admission.utils.Utility.comboBoxScroll(partComboBox);
        admission.utils.Utility.comboBoxScroll(orientedComboBox);
        admission.utils.Utility.comboBoxScroll(disciplineComboBox);
        
        setLocationRelativeTo(null);
    }

    @Override
    public void setVisible(boolean b) {
        getModelMapping();
        loadCatalog();
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        addFacultyButton = new javax.swing.JButton();
        addPartButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        partComboBox = new javax.swing.JComboBox();
        addProgramButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        programComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        disciplineComboBox = new javax.swing.JComboBox();
        addDisciplineButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        addOrientedButton = new javax.swing.JButton();
        orientedComboBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        modelMappingList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Model Mapping");
        setResizable(false);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Go-Previous-32.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setFocusCycleRoot(true);
        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Faculty:");

        facultyComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });

        addFacultyButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addFacultyButton.setText("Add");
        addFacultyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacultyButtonActionPerformed(evt);
            }
        });

        addPartButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addPartButton.setText("Add");
        addPartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPartButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Part:");

        partComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        addProgramButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addProgramButton.setText("Add");
        addProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProgramButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Program:");

        programComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Discipline:");

        disciplineComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        addDisciplineButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addDisciplineButton.setText("Add");
        addDisciplineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDisciplineButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Oriented:");

        addOrientedButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addOrientedButton.setText("Add");
        addOrientedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrientedButtonActionPerformed(evt);
            }
        });

        orientedComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(orientedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(programComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(partComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facultyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disciplineComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addPartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addProgramButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addDisciplineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addOrientedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addFacultyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addDisciplineButton, addFacultyButton, addOrientedButton, addPartButton, addProgramButton});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {disciplineComboBox, facultyComboBox, orientedComboBox, partComboBox, programComboBox});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFacultyButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disciplineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addDisciplineButton)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(programComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProgramButton)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPartButton)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orientedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addOrientedButton)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jScrollPane2.setViewportView(modelMappingList);

        jLabel1.setText("Included:");

        titlePanel.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Media-Playlist-Shuffle-40.png"))); // NOI18N
        jLabel8.setText("Model Mapping");
        jLabel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        titlePanel.add(jLabel8, java.awt.BorderLayout.CENTER);

        nameLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nameLabel.setText("Name");

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gnome-Edit-Delete-32.png"))); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(deleteButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if(JOptionPane.YES_OPTION != MessageBox.confirm2(this, MessageEnum.MSG_DELETE_QUESTION)) {
            return;
        }
        ModelMapping mm = (ModelMapping) modelMappingList.getSelectedValue();
        if (mm == null) {
            return;
        }

        try {
            DatabaseManager.deleteData(mm);
            getModelMapping();
            loadCatalog();
        } catch (HibernateException ex) {
            MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addFacultyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacultyButtonActionPerformed
        Faculty faculty = (Faculty) facultyComboBox.getSelectedItem();
        if (faculty == null) {
            return;
        }

        try {
            ModelMapping mm = new ModelMapping();
            mm.setFeeModel(feeModel);
            mm.setModelId(faculty.getFacultyId());
            mm.setModelName(Faculty.class.getName());
            DatabaseManager.save(mm);
            getModelMapping();
            
            facultyComboBox.removeItem(faculty);
        } catch (HibernateException ex) {
            MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_addFacultyButtonActionPerformed

    private void addDisciplineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDisciplineButtonActionPerformed
        Discipline discipline = (Discipline) disciplineComboBox.getSelectedItem();
        if (discipline == null) {
            return;
        }

        try {
            ModelMapping mm = new ModelMapping();
            mm.setFeeModel(feeModel);
            mm.setModelId(discipline.getDisciplineId());
            mm.setModelName(Discipline.class.getName());
            DatabaseManager.save(mm);
            getModelMapping();
            
            disciplineComboBox.removeItem(discipline);
        } catch (HibernateException ex) {
            MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_addDisciplineButtonActionPerformed

    private void addProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProgramButtonActionPerformed
        Program program = (Program) programComboBox.getSelectedItem();
        if (program == null) {
            return;
        }

        try {
            ModelMapping mm = new ModelMapping();
            mm.setFeeModel(feeModel);
            mm.setModelId(program.getProgramId());
            mm.setModelName(Program.class.getName());
            DatabaseManager.save(mm);
            getModelMapping();
            
            programComboBox.removeItem(program);
        } catch (HibernateException ex) {
            MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_addProgramButtonActionPerformed

    private void addPartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPartButtonActionPerformed
        Part part = (Part) partComboBox.getSelectedItem();
        if (part == null) {
            return;
        }

        try {
            ModelMapping mm = new ModelMapping();
            mm.setFeeModel(feeModel);
            mm.setModelId(part.getPartId());
            mm.setModelName(Part.class.getName());
            DatabaseManager.save(mm);
            getModelMapping();
            
            partComboBox.removeItem(part);
        } catch (HibernateException ex) {
            MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_addPartButtonActionPerformed

    private void addOrientedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrientedButtonActionPerformed
        OrientedEnum oriented = (OrientedEnum) orientedComboBox.getSelectedItem();
        if (oriented == null) {
            return;
        }

        try {
            ModelMapping mm = new ModelMapping();
            mm.setFeeModel(feeModel);
            mm.setModelId(oriented.ordinal());
            mm.setModelName(OrientedEnum.class.getName());
            DatabaseManager.save(mm);
            getModelMapping();
        } catch (HibernateException ex) {
            MessageBox.error(this, ex);
        }
    }//GEN-LAST:event_addOrientedButtonActionPerformed

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        getDiscipline();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    public void setFeeModel(FeeModel feeModel) {
        this.feeModel = feeModel;
        this.nameLabel.setText(feeModel.getName());
        programType = DatabaseManager.getSingleRecord(ProgramType.class, "isBachelor <> " + feeModel.getProgramType().ordinal());
    }

    private void getModelMapping() {
        modelMappingDataList = DatabaseManager.getData(ModelMapping.class, "feeModel.id = " + feeModel.getId(), "modelName");
        modelMappingList.setListData(modelMappingDataList.toArray());
    }

    private void loadCatalog() {
        getFaculty();
        getPart();
        getProgram();
//        getDiscipline();
        getOriented();
    }

    private void getFaculty() {
        facultyComboBox.removeAllItems();
        List<Faculty> list = DatabaseManager.getData(Faculty.class, "displayOrder");
        for (Faculty f : list) {
            LABEL:
            {
                for (ModelMapping mm : modelMappingDataList) {
                    if (mm.getModelName().equals(Faculty.class.getName())
                            && mm.getModelId() == f.getFacultyId().intValue()) {
                        break LABEL;
                    }
                }
                facultyComboBox.addItem(f);
            }
        }
    }

    private void getPart() {
        partComboBox.removeAllItems();
        List<Part> list = DatabaseManager.getData(Part.class, "programType.programTypeId = " + programType.getProgramTypeId(), "name");
        for (Part part : list) {
            LABEL:
            {
                for (ModelMapping mm : modelMappingDataList) {
                    if (mm.getModelName().equals(Part.class.getName())
                            && mm.getModelId() == part.getPartId().intValue()) {
                        break LABEL;
                    }
                }
                partComboBox.addItem(part);
            }
        }
    }

    private void getProgram() {
        programComboBox.removeAllItems();
        List<Program> list = DatabaseManager.getData(Program.class, "programType.programTypeId = " + programType.getProgramTypeId(), "name");
        for (Program p : list) {
            LABEL:
            {
                for (ModelMapping mm : modelMappingDataList) {
                    if (mm.getModelName().equals(Program.class.getName())
                            && mm.getModelId() == p.getProgramId().intValue()) {
                        break LABEL;
                    }
                }
                programComboBox.addItem(p);
            }
        }
    }

    private void getDiscipline() {
        disciplineComboBox.removeAllItems();

        Faculty faculty = (Faculty) facultyComboBox.getSelectedItem();
        if (faculty == null) {
            return;
        }

        String hql = "SELECT d FROM Discipline AS d "
                + "INNER JOIN d.department AS de "
                + "INNER JOIN de.faculty AS fa "
                + "WHERE fa.facultyId = " + faculty.getFacultyId() + " "
                + "ORDER BY d.name";

        List<Discipline> list = DatabaseManager.executeQuery(Discipline.class, hql);
        for (Discipline d : list) {
            LABEL:
            {
                for (ModelMapping mm : modelMappingDataList) {
                    if (mm.getModelName().equals(Discipline.class.getName())
                            && mm.getModelId() == d.getDisciplineId().intValue()) {
                        break LABEL;
                    }
                }
                disciplineComboBox.addItem(d);
            }
        }
    }

    private void getOriented() {
        orientedComboBox.removeAllItems();
        OrientedEnum[] values = OrientedEnum.values();
        for (OrientedEnum oe : values) {
            LABEL:
            {
                for (ModelMapping mm : modelMappingDataList) {
                    if (mm.getModelName().equals(OrientedEnum.class.getName())
                            && mm.getModelId() == oe.ordinal()) {
                        break LABEL;
                    }
                }
                orientedComboBox.addItem(oe);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDisciplineButton;
    private javax.swing.JButton addFacultyButton;
    private javax.swing.JButton addOrientedButton;
    private javax.swing.JButton addPartButton;
    private javax.swing.JButton addProgramButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox disciplineComboBox;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList modelMappingList;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox orientedComboBox;
    private javax.swing.JComboBox partComboBox;
    private javax.swing.JComboBox programComboBox;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
    private FeeModel feeModel;
    private ProgramType programType;
    private List<ModelMapping> modelMappingDataList;
}
